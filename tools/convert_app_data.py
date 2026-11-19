import json
import re
from pathlib import Path
ROOT = Path(__file__).resolve().parent.parent
SOURCE = ROOT / 'data/raw/app_data.dart'
OUTPUT = ROOT / 'data/reviewed'
OUTPUT.mkdir(parents=True, exist_ok=True)
text = SOURCE.read_text(encoding='utf-8-sig')

def extract_array(key):
    match = re.search(r'["\']' + re.escape(key) + r'["\']\s*:\s*\[', text)
    if not match:
        raise ValueError(f'Missing section: {key}')
    start = match.end()-1
    depth=0; quote=None; escaped=False
    for i in range(start,len(text)):
        ch=text[i]
        if quote:
            if escaped: escaped=False
            elif ch=='\\': escaped=True
            elif ch==quote: quote=None
        elif ch in ('"', "'"): quote=ch
        elif ch=='[': depth+=1
        elif ch==']':
            depth-=1
            if depth==0:
                fragment=text[start:i+1]
                fragment=re.sub(r',\s*([}\]])',r'\1',fragment)
                return json.loads(fragment)
    raise ValueError('Unclosed array: '+key)

def save(name, items):
    path=OUTPUT/name
    path.write_text(json.dumps(items,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
    print(f'{name}: {len(items)} entries')

if __name__=='__main__':
    save('alphabet.json',[{**x,'verified':False} for x in extract_array('alphabet')])
    save('numbers.json',[{**x,'value':i,'verified':False} for i,x in enumerate(extract_array('numbers'),1)])
    save('learning_alphabet.json',[{**x,'verified':False} for x in extract_array('alphabets')])
    vocabulary=[]
    for section in ('living_things',):
        for category in extract_array(section):
            for item in category.get('items',[]):
                vocabulary.append({**item,'category':category.get('name_en',section),'verified':False})
    save('vocabulary.json',vocabulary)
    words=[]
    for x in vocabulary:
        raw=x.get('name_ol','')
        word=raw.split(' (')[0].strip()
        if word and word not in {w['word'] for w in words}:
            words.append({'word':word,'roman':raw.split(' (',1)[1].rstrip(')') if ' (' in raw else '', 'english':x.get('name_en',''), 'hindi':x.get('name_hi',''), 'category':x['category'], 'frequency':1, 'verified':False})
    save('dictionary.json',words)

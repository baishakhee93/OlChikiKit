import json
from pathlib import Path
base=Path(__file__).resolve().parent.parent/'data'/'reviewed'
for filename in ('alphabet.json','numbers.json','learning_alphabet.json','vocabulary.json','dictionary.json','sentences.json'):
    items=json.loads((base/filename).read_text(encoding='utf-8'))
    assert isinstance(items,list), filename
    assert all(isinstance(item,dict) for item in items), filename
    print(f'{filename}: {len(items)} records OK (structural check only)')
words=json.loads((base/'dictionary.json').read_text(encoding='utf-8'))
assert all(isinstance(x.get('word'),str) and x['word'] for x in words)
sentences=json.loads((base/'sentences.json').read_text(encoding='utf-8'))
assert all(isinstance(x.get('sentence_ol'),str) and x['sentence_ol'] for x in sentences)
print('Structural validation passed. Linguistic verification still required.')

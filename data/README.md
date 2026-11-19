# Language data preparation

- `raw/app_data.dart` is the original uploaded Dart source, retained unchanged.
- `reviewed/*.json` are **automatically extracted candidates**, NOT linguistically reviewed. Every record has `verified: false`.
- `alphabet.json`: 30 original alphabet records.
- `numbers.json`: 100 original number records, with numeric `value` added; original Hindi strings preserved (some mix digit scripts).
- `learning_alphabet.json`: 26 English alphabet example records.
- `vocabulary.json`: 120 flattened living-things entries.
- `dictionary.json`: 120 candidate Ol Chiki words extracted from vocabulary; frequency defaults to 1.
- `sentences.json`: 100 curated Santali sentences with translations and categories.

## Regenerate data

From the repository root: `py tools/convert_app_data.py` (Windows) or `python tools/convert_app_data.py`.

**Warning:** Regeneration overwrites the JSON files. Back up reviewed corrections first; once manually reviewed, use a separate curated source and do not rerun this generator over it.

## Runtime integration

The SDK includes `DictionaryLoader` to parse reviewed JSON data files into `DictionaryEntry` objects. Never present `verified:false` data as authoritative translations.

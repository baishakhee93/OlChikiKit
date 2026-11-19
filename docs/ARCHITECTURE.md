# SDK architecture

`OlChikiUnicode` validates code points in the Ol Chiki block. `KeyboardLayouts` stores platform-independent key rows. `Transliterator` uses longest-prefix matching of a configurable rule map. `OfflineDictionary` indexes word entries and ranks prefix matches by descending frequency. `SuggestionEngine` provides up to N candidate strings.

The dictionary is an in-memory starter implementation with linear prefix scanning; for large corpora, implement a trie or persistent indexed store. No network, analytics, keystroke capture, Firebase, or Android IME code is included.

Production next steps: native-speaker validation; standardized transliteration scheme; licensed frequency corpus; optimized prefix index; correction rules; integration with app IME and secure field handling; benchmarking and compatibility tests.

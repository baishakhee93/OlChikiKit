package example

import io.github.baishakhee93.olchikikit.*

fun main() {
    println("--- OlChikiKit JVM Example ---")
    println("Roman johar -> ${Transliterator().transliterate("johar")}")
    
    val sampleJson = """
        [
          {"word": "johar", "roman": "johar", "english": "hello", "frequency": 20},
          {"word": "jom", "roman": "jom", "english": "eat", "frequency": 10},
          {"word": "aam", "roman": "aam", "english": "you", "frequency": 30}
        ]
    """.trimIndent()
    
    val entries = DictionaryLoader.parseJson(sampleJson)
    val dictionary = OfflineDictionary(entries)
    val suggestions = SuggestionEngine(dictionary).suggest("jo")
    
    println("Loaded dictionary entries: ${dictionary.size()}")
    println("Suggestions for prefix 'jo': $suggestions")
}

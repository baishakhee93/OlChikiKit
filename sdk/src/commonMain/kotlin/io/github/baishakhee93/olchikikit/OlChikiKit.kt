package io.github.baishakhee93.olchikikit

object OlChikiKit {
    fun containsOlChiki(text: String) = OlChikiUnicode.containsOlChiki(text)
    fun transliterate(text: String) = Transliterator().transliterate(text)
    fun suggestions(prefix: String, dictionary: OfflineDictionary, limit: Int = 3) = SuggestionEngine(dictionary).suggest(prefix, limit)
}

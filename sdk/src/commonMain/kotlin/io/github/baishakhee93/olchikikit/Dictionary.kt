package io.github.baishakhee93.olchikikit

/** In-memory offline dictionary. Host app can load curated words from its own assets/database. */
data class DictionaryEntry(val word: String, val frequency: Int = 1) {
    init { require(word.isNotBlank()); require(frequency >= 0) }
}
class OfflineDictionary(entries: Iterable<DictionaryEntry> = emptyList()) {
    private val entriesByWord = mutableMapOf<String, DictionaryEntry>()
    init { addAll(entries) }
    fun addAll(entries: Iterable<DictionaryEntry>) {
        entries.forEach { entry ->
            val key = entry.word.lowercase()
            val previous = entriesByWord[key]
            if (previous == null || entry.frequency > previous.frequency) entriesByWord[key] = entry
        }
    }
    fun contains(word: String): Boolean = entriesByWord.containsKey(word.lowercase())
    fun size(): Int = entriesByWord.size
    fun findByPrefix(prefix: String, limit: Int = 3): List<DictionaryEntry> {
        if (prefix.isBlank() || limit <= 0) return emptyList()
        val normalized = prefix.lowercase()
        return entriesByWord.asSequence().filter { (word, _) -> word.startsWith(normalized) }
            .map { it.value }.sortedWith(compareByDescending<DictionaryEntry> { it.frequency }.thenBy { it.word })
            .take(limit).toList()
    }
}
class SuggestionEngine(private val dictionary: OfflineDictionary) {
    fun suggest(prefix: String, limit: Int = 3): List<String> = dictionary.findByPrefix(prefix, limit).map { it.word }
}

package io.github.baishakhee93.olchikikit

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DictionaryItemDto(
    val word: String,
    val roman: String? = null,
    val english: String? = null,
    val hindi: String? = null,
    val category: String? = null,
    val frequency: Int = 1,
    val verified: Boolean = false
)

object DictionaryLoader {
    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    /**
     * Parses a JSON string containing an array of dictionary entries into a list of [DictionaryEntry].
     * Returns an empty list if the input is malformed, blank, or invalid.
     */
    fun parseJson(jsonString: String): List<DictionaryEntry> {
        return try {
            if (jsonString.isBlank()) return emptyList()
            val items = jsonParser.decodeFromString<List<DictionaryItemDto>>(jsonString)
            items.map { DictionaryEntry(it.word, it.frequency) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

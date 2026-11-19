package io.github.baishakhee93.olchikikit

import kotlin.test.*

class OlChikiKitTest {
    @Test fun unicodeBlock() {
        assertTrue(OlChikiUnicode.isOlChiki('ᱚ'))
        assertFalse(OlChikiUnicode.isOlChiki('A'))
        assertTrue(OlChikiKit.containsOlChiki("Hello ᱡᱚᱦᱟᱨ"))
        assertEquals(10, OlChikiUnicode.digits.size)
    }
    @Test fun layouts() {
        assertEquals("ol-chiki", KeyboardLayouts.olChiki.id)
        assertEquals(3, KeyboardLayouts.english.rows.size)
        assertNull(KeyboardLayouts.byId("unknown"))
    }
    @Test fun longestMatchTransliteration() {
        assertEquals("ᱝ", Transliterator().transliterate("ng"))
        assertEquals("ᱡᱚᱦᱟᱨ", Transliterator().transliterate("johar"))
        assertEquals("!", Transliterator().transliterate("!"))
    }
    @Test fun suggestionsRankAndLimit() {
        val dictionary = OfflineDictionary(listOf(
            DictionaryEntry("ᱡᱚᱦᱟᱨ", 20), DictionaryEntry("ᱡᱚᱦᱟᱨᱮ", 5), DictionaryEntry("ᱟᱢ", 30)
        ))
        assertEquals(listOf("ᱡᱚᱦᱟᱨ", "ᱡᱚᱦᱟᱨᱮ"), SuggestionEngine(dictionary).suggest("ᱡᱚ"))
        assertEquals(emptyList(), SuggestionEngine(dictionary).suggest(""))
        assertTrue(dictionary.contains("ᱟᱢ"))
    }
    @Test fun dictionaryJsonLoading() {
        val json = """
            [
              {"word": "ᱡᱚᱦᱟᱨ", "roman": "johar", "english": "hello, world", "frequency": 15},
              {"word": "ᱡᱚᱢ", "roman": "jom", "english": "eat \"good\"", "frequency": 10}
            ]
        """.trimIndent()
        val entries = DictionaryLoader.parseJson(json)
        val dictionary = OfflineDictionary(entries)
        assertEquals(2, dictionary.size())
        assertEquals(listOf("ᱡᱚᱦᱟᱨ", "ᱡᱚᱢ"), SuggestionEngine(dictionary).suggest("ᱡᱚ"))
    }
    @Test fun malformedJsonHandling() {
        val malformed = "{ invalid_json_array }"
        val entries = DictionaryLoader.parseJson(malformed)
        assertTrue(entries.isEmpty())
    }
}

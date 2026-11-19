package io.github.baishakhee93.olchikikit

/** Experimental Roman-to-Ol-Chiki transliteration. Not a certified orthographic standard. */
class Transliterator(private val rules: Map<String, String> = DEFAULT_RULES) {
    private val orderedRules = rules.entries.sortedWith(compareByDescending<Map.Entry<String, String>> { it.key.length }.thenBy { it.key })
    fun transliterate(input: String): String = buildString {
        var index = 0
        while (index < input.length) {
            val match = orderedRules.firstOrNull { input.regionMatches(index, it.key, 0, it.key.length, ignoreCase = true) }
            if (match != null) { append(match.value); index += match.key.length }
            else { append(input[index]); index++ }
        }
    }
    companion object {
        val DEFAULT_RULES = mapOf(
            "ng" to "ᱝ", "ny" to "ᱧ", "ch" to "ᱪ", "th" to "ᱛ", "ph" to "ᱯ",
            "a" to "ᱟ", "i" to "ᱤ", "u" to "ᱩ", "e" to "ᱮ", "o" to "ᱚ",
            "k" to "ᱠ", "g" to "ᱜ", "l" to "ᱞ", "s" to "ᱥ", "h" to "ᱦ",
            "j" to "ᱡ", "m" to "ᱢ", "n" to "ᱱ", "d" to "ᱫ", "b" to "ᱵ",
            "p" to "ᱯ", "r" to "ᱨ", "t" to "ᱛ", "w" to "ᱣ", "y" to "ᱭ"
        )
    }
}

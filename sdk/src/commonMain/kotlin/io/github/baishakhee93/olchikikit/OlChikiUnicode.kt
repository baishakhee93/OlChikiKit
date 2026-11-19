package io.github.baishakhee93.olchikikit

/** Unicode Ol Chiki block: U+1C50..U+1C7F. */
object OlChikiUnicode {
    const val START = 0x1C50
    const val END = 0x1C7F
    fun isOlChiki(char: Char): Boolean = char.code in START..END
    fun containsOlChiki(text: String): Boolean = text.any(::isOlChiki)
    fun isEntirelyOlChiki(text: String): Boolean = text.isNotEmpty() && text.all(::isOlChiki)
    fun characters(): List<Char> = (START..END).map(Int::toChar)
    val digits: List<Char> = (0x1C50..0x1C59).map(Int::toChar)
}

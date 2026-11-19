package io.github.baishakhee93.olchikikit

data class KeyboardKey(val output: String, val label: String = output)
data class KeyboardLayout(val id: String, val rows: List<List<KeyboardKey>>)

/** Layout data only; native system IMEs remain in application repositories. */
object KeyboardLayouts {
    val olChiki = KeyboardLayout("ol-chiki", listOf(
        "ᱧ ᱝ ᱚ ᱟ ᱤ ᱩ ᱮ ᱳ ᱴ ᱵ".split(' ').map(::KeyboardKey),
        "ᱠ ᱜ ᱞ ᱥ ᱦ ᱡ ᱢ ᱱ ᱫ".split(' ').map(::KeyboardKey),
        "ᱪ ᱬ ᱭ ᱯ ᱨ ᱛ ᱣ ᱹ ᱺ".split(' ').map(::KeyboardKey)
    ))
    val english = KeyboardLayout("en-qwerty", listOf(
        "qwertyuiop".map { KeyboardKey(it.toString()) },
        "asdfghjkl".map { KeyboardKey(it.toString()) },
        "zxcvbnm".map { KeyboardKey(it.toString()) }
    ))
    val olChikiNumbers = KeyboardLayout("ol-chiki-numbers", listOf(OlChikiUnicode.digits.map { KeyboardKey(it.toString()) }))
    fun byId(id: String): KeyboardLayout? = listOf(olChiki, english, olChikiNumbers).firstOrNull { it.id == id }
}

package org.kshot.snapshots

const val INDENT_WIDTH = 2

// ref: https://gist.github.com/mayankmkh/92084bdf2b59288d3e74c3735cccbf9f
fun prettyPrint(str: String): String {
    var indentLevel = 0
    fun padding() = "".padStart(indentLevel * INDENT_WIDTH)
    val stringBuilder = StringBuilder(str.length)

    var i = 0
    while (i < str.length) {
        when (val char = str[i]) {
            '(', '[', '{' -> {
                indentLevel++
                stringBuilder.appendLine(char).append(padding())
            }
            ')', ']', '}' -> {
                indentLevel--
                stringBuilder.appendLine().append(padding()).append(char)
            }
            ',' -> {
                stringBuilder.appendLine(char).append(padding())
                // ignore space after comma as we have added a newline
                val nextChar = str.getOrElse(i + 1) { char }
                if (nextChar == ' ') i++
            }
            else -> {
                stringBuilder.append(char)
            }
        }
        i++
    }

    return stringBuilder.toString().replace("=", " = ")
}

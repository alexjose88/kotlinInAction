package dsl


fun main() {
    val s1 = buildStringWithoutReceiver {
        it.append("hello") //optional to use this.
        it.append("how")
    }

    println(s1)

    val s2 = buildStringWithReceiver {
        this.append("hello") //optional to use this.
        append("how")
    }

    println(s2)

    val addExclamation: StringBuilder.() -> Unit = { append("!") }

    val stringBuilder = StringBuilder()

    stringBuilder.addExclamation()
    addExclamation(stringBuilder)

    println(stringBuilder.toString())



    val map = mutableMapOf("1" to "2")

    val modifyFirst: MutableMap<String, String>.() -> Unit = { this["1"] = "3" }
    map.modifyFirst()
    modifyFirst(map)

    map.apply { this["1"] = "3" }

    with(map, modifyFirst)
    with(map) { this["1"] = "3" }

    println(map)
}


fun buildStringWithoutReceiver(
    builderAction: (StringBuilder) -> Unit
): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}


fun buildStringWithReceiver(
    builderAction: StringBuilder.() -> Unit //lambda with receiver
): String {
    val sb = StringBuilder()
    builderAction(sb)

    return sb.toString()
}
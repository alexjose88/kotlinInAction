package dsl

fun main() {

    //Example 1
    val createTable = {
        table {
            tr {
                td {

                }
            }
        }
    }

    println(createTable().toString())

    //Example 2
    val createAnotherTable = {
        table {
            for(number in 1..5) {
                tr {  }
            }
        }
    }

    println(createAnotherTable().toString())

}

fun table(init: TABLE.() -> Unit) : TABLE = TABLE().apply(init)


open class Tag(private val name : String) {
    private val children: MutableList<Tag> = mutableListOf()

    protected fun <T: Tag> doInit(tag: T, init: T.() -> Unit): T {
        children.add(tag)
        return tag.apply(init)
    }

    override fun toString(): String {
        return "<${name}>${children.joinToString("") }</${name}>"
    }
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit): TR =
        doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")
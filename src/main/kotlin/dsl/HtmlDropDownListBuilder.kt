package dsl

fun main() {
    val createDropDown =
        {
            html {
                div(classes = "dropdown") {
                    ul(classes = "dropdown-menu") {
                        li { a("#", "Action") }
                        li { role = "separator"; classes = "divider" }
                    }
                }
            }
        }

    println(createDropDown().toString())


    val createDropDownWithExplicitFunctions =
        {
            html {
                div(classes = "dropdown") {
                    dropdown {
                        item(href = "#", "Action")
                        separator()
                    }
                }
            }
        }

    println(createDropDownWithExplicitFunctions())
}

private operator fun String.unaryPlus(): String {
    return this
}

fun html(init: HTML.() -> Unit) = HTML().apply(init)

open class TagHTML(private val name: String, private val classes: Set<String>) {
    private val children: MutableList<TagHTML> = mutableListOf()

    protected fun <T : TagHTML> doInit(tag: T, init: T.() -> Unit = {}): T {
        children.add(tag)
        return tag.apply(init)
    }

    override fun toString(): String {
        return "<${name} class='${classes.joinToString(" ")}'>${children.joinToString("")}</${name}>"
    }
}

class HTML : TagHTML("html", setOf()) {
    fun div(classes: String = "", init: DIV.() -> Unit) = doInit(DIV(classes), init)
}

class DIV(private val classes: String = "") : TagHTML("div", (classes as CharSequence).split(" ").toSet()) {
    fun ul(classes: String = "", init: UL.() -> Unit) = doInit(UL(classes), init)
}

class UL(private val classes: String = "") : TagHTML("ul", (classes as CharSequence).split(" ").toSet()) {
    fun li(init: LI.() -> Unit) = doInit(LI(), init)
}

class LI(
    var role: String? = null,
    var classes: String = ""
) : TagHTML("li", (classes as CharSequence).split(" ").toSet()) {
    fun a(href: String, action: String) = doInit(A(href, action))
}

class A(
    val href: String,
    val action: String,
    private val classes: String = ""
) : TagHTML("a", (classes as CharSequence).split(" ").toSet())


//Creating extension function in order to have more explicit code


fun DIV.dropdown(block: UL.() -> Unit) = ul(classes = "dropdown-menu", block)

fun UL.item(href: String, action: String): LI = li { a(href = href, action = action) }
fun UL.separator(): LI = li { role = "separator"; classes = "divider" }

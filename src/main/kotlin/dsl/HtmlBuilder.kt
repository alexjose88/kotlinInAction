package dsl

fun main() {

    TABLE({
        TR {
          TD()
        }
    }


}

open class Tag

class TABLE : Tag {
    fun tr(init: TR.() -> Unit)
}

class TR : Tag {
    fun td(init: TD.() -> Unit)
}

class TD : Tag()



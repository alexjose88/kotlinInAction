package lambdas

class EmptyPerson {
    private var name: String? = null
    private var age: Int? = null

    fun addName(name: String) {
        this.name = name
    }

    fun addAge(age: Int) {
        this.age = age
    }

    fun getName(): String? = name

    fun getAge(): Int? = age
}

fun main() {

    //Return a presentation sentence without receiver functions
    val person1 = EmptyPerson()
    person1.addName("Some name")
    person1.addAge(15)
    println("Hello my name is ${person1.getName()} and I'm ${person1.getAge()}")


    //with() returns the result of the expression
    println(
        with(EmptyPerson()) {
            addName("Some name")
            addAge(15)
            "Hello my name is ${this.getName()} and I'm ${getAge()}"
        }
    )


    //apply() returns the passed object
    val person2 = EmptyPerson().apply {
        addName("some name")
        addAge(15)
    }
    println("Hello my name is ${person2.getName()} and I'm ${person2.getAge()}")
}
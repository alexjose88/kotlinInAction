package conventions

fun main () {
    val person = Person(name = "some name", surname = "some surname")
    val (name, surname) = person //Works because the data class Person already implements the destructuring of components

    println("Name is $name surname is $surname")

    val otherPerson = NormalClassPerson(name = "another name", age = 10)
    val (otherPersonName, age) = otherPerson //Works because the NormalClassPerson implements the destructuring of components

    println("Name is $otherPersonName and age $age")
}


class NormalClassPerson(val name : String, val age : Int) {
    operator fun component1() = name
    operator fun component2() = age
}
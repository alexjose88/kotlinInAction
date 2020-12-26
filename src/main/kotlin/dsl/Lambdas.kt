package dsl


//Method that expects a function as an argument
fun createGreet(personInit: (EmptyPerson) -> Unit): String {
    val person = EmptyPerson()
    personInit(person) // Here we are using a method that expects an instance of class EmptyPerson
    return "Hello my name is ${person.name} and I'm ${person.age} years old"
}

fun main() {

    // Call to the method that expects another method as argument, as it's the only argument, this can be outside parentheses
    // the "it" referencing the instance has to be present
    println(
        createGreet {
            it.name = "DummyName"
            it.age = 22
        }
    )

    //To not
    //The "it" referencing the instance is not present in this case
}
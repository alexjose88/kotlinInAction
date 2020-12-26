package dsl

//Method expects a receiver function as an argument
fun createGreetWithReceiver(personInit: EmptyPerson.() -> Unit): String {
    val person = EmptyPerson()
    person.personInit() // Here we are using an extension function of the class EmptyPerson
    return "Hello my name is ${person.name} and I'm ${person.age} years old"
}

fun main() {


    //Scenario 1
    println(
        //Call to a method that expects a receiver function
        //In this case we don't have to use the "it" to reference the instance
        createGreetWithReceiver {
            this.name = "DummyName"
            age = 22
        }
    )

    //Scenario 2
    //Same way by specifying the function with receiver as a value of a variable
    val initPerson: EmptyPerson.() -> Unit = {
        this.name = "DummyName"
        age = 22
    }

    println(
        createGreetWithReceiver(initPerson)
    )


    //Scenario 3
    val createGreeting : EmptyPerson.() -> String = {
        "Hello my name is $name and I'm $age years old"
    }

    //"apply" returns the instance of the which called the method
    val emptyPerson = EmptyPerson().apply(initPerson)
    println(
        //"with" returns the last value of the expression
        with(emptyPerson, createGreeting)
    )

}
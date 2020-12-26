package lambdas

fun someTopLevelFunction() {
    println("I'm a top level function")
}

fun Person.isUnderAge(): Boolean = age < 18

fun main() {
    val person1 = Person(name = "name1", age = 20)
    val person2 = Person(name = "name 2", age = 25)
    val persons = listOf(person1, person2)

    // Executing a top level function by specifying its reference to a lambda e.g. run()
    run(::someTopLevelFunction)

    // Executing an extension function by specifying its reference from the class's method
    val isUnderAge = Person::isUnderAge
    run { println(isUnderAge(person2)) }

    // Executing an extension function by specifying its reference from the instance's method
    val isUnderAge2 = person2::isUnderAge
    run { println(isUnderAge2()) }

    // Using a class member property in a lambda
    println(persons.maxOf(Person::age))
}
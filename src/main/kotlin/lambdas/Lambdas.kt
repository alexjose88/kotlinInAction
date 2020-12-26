package lambdas

fun main() {
    val person1 = Person(name = "name1", age = 20)
    val person2 = Person(name = "name 2", age = 25)
    val persons = listOf(person1, person2)

    //library function using lambda to get the max age
    println(persons.maxOf { it.age }) // if lambda is the only argument, we don't need parentheses
    println(persons.maxOf() { it.age })

    //library function using member reference to get the max age
    println(persons.maxOf(Person::age))

    // lambda syntax, stored in a variable the -> separates between arguments from the body
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))

    // using lambda in function with more than one argument
    println(persons.joinToString(separator = " ", transform = { p: Person -> p.name }))
    println(persons.joinToString(" ") { p: Person -> p.name }) // if lambda last argument, we can move it outside parentheses


    // Lambdas capture variables from outside the lambda
    val prefix = "Name: "
    persons.forEach {
        println("$prefix${it.name}")
    }

}

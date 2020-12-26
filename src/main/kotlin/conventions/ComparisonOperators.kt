package conventions

fun main() {

    val point = Point(1, 1)

    //Usage of the comparator method defined in class Point
    println(point == null)
    println(point == Point(1, 1))
    println(point == Point(1, 2))
    println(point.equals("random string"))
    println(point != Point(2, 1))

    val personA = Person(name = "a", surname = "a")
    val personB = Person(name = "a", surname = "b")
    val personC = Person(name = "a", surname = "a")

    println(personA < personB) //Using the convention function compareTo in class Person
    println(personA <= personC)


}

package conventions

fun main() {
    //Usage of the invoke method defined in this case as an extension function of class Person
    val person1 = Person("SomeName", "SomeSurname")
    person1("Some random text") //Call the invoke() method

    val person2 = Person("Dummy name", "A surname")
    val personList = listOf(person1, person2)

    val complexPersonFilter = ComplexPersonFilter()
    println(personList.filter(complexPersonFilter)) // Usage of the instance of the complex filter class through the invoke method
}

operator fun Person.invoke(text: String) {
    println("Calling for person $name with text: $text")
}

//Class extending from a function type, in this case for a complex filter
class ComplexPersonFilter : (Person) -> Boolean {
    override fun invoke(person: Person): Boolean {
        return isNameBetweenDandG(person) && isSurnameBetweenAandC(person)
    }

    private fun isSurnameBetweenAandC(person: Person): Boolean {
        return person.surname in "A".."C"
    }

    private fun isNameBetweenDandG(person: Person): Boolean {
        return person.name in "D".."G"
    }
}
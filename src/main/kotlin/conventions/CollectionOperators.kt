package conventions

fun main() {
    val personCollection = PersonCollection()

    println(personCollection[0])
    personCollection[0] =
        Person("name 1", "surname 1") // Usage of the operator overloading "set" in class PersonCollection
    println(personCollection[0]) // Usage of the operator overloading "get" in class PersonCollection

    personCollection[10] = Person("name 2", "surname 2")
    println(personCollection[10])

    println(personCollection["surname 2"]) //Usage of the operator overloading "get" that expects a string
    println(personCollection["name 1", "surname 1"]) //Usage of operator "get" with multiple strings

    //Usage of operator "contains"
    println(Person("name 1", "surname 1") in personCollection)
    println(Person("name random", "surname random") in personCollection)

    //Usage of iterator
    for (person in personCollection) {
        println(person)
    }


}

class PersonCollection {
    private val collection = mutableMapOf<Int, Person>()

    operator fun get(index: Int): Person? {
        return collection[index]
    }

    operator fun get(surname: String): Person? {
        return collection.values.firstOrNull { person -> person.surname == surname }
    }

    operator fun get(name: String, surname: String): Person? {
        return collection.values.firstOrNull { person -> person.name == name && person.surname == surname }
    }

    operator fun set(index: Int, person: Person) {
        collection[index] = person
    }

    operator fun contains(person: Person): Boolean {
        return collection.values.contains(person)
    }

    //Custom iterator
    operator fun iterator(): Iterator<Person> {
        return object : Iterator<Person> {
            var currentIndex = 0
            var current = collection.values.elementAt(currentIndex)
            override fun hasNext(): Boolean = (currentIndex + 1) <= collection.values.size
            override fun next(): Person {
                currentIndex += 1
                current = collection.values.elementAt(currentIndex - 1)// Doing this because the first element is not included

                return current
            }
        }
    }

    /*
    //Returning the iterator object from the collection containing the values of the personCollection map
    operator fun iterator(): Iterator<Person> {
        return collection.values.iterator()
    }

     */
}
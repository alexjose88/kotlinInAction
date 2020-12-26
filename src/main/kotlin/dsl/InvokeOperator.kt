package dsl

fun main() {
    val personCollection = PersonCollection()
    personCollection.addPerson(EmptyPerson()) //Calling the addPerson() method in a regular way
    personCollection.removeLastInsertedPerson() //Calling the removeLastInsertedPerson() in a regular way

    //This lambda is calling the invoke method for the instance of personCollection
    personCollection {
        addPerson(EmptyPerson())
        removeLastInsertedPerson()
    }

    //Same sequence of the previous lambda, in this case stored in a function type variable with a receiver
    val personsScript : PersonCollection.() -> Unit = {
        addPerson(EmptyPerson())
        removeLastInsertedPerson()
    }

    //Call of the previously defined function with receiver
    personCollection.personsScript()
}

//In case we need to support both regular and from lambda calls, we create the operator invoke() with a receiver function
//of the same class
class PersonCollection {
    private val collection = mutableListOf<EmptyPerson>()

    fun addPerson(person: EmptyPerson) {
        collection.add(person)
        println("Person added to the collection")
    }

    fun removeLastInsertedPerson() {
        collection.removeLast()
        println("Last inserted person removed from collection")
    }

    operator fun invoke(body: PersonCollection.() -> Unit) = body()
}
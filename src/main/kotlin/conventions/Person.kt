package conventions

data class Person(val name: String, val surname: String) : Comparable<Person> {

    override fun compareTo(other: Person): Int {
        //Executes the first callback function and if the comparison is the same, executes the next if exists otherwise
        //returns 0
        //In this case the first callback is this.surname compared to other.surname

        return compareValuesBy(this, other, Person::surname, Person::name)

        /* Same can be done by passing lambdas
        return compareValuesBy(
            this,
            other,
            { person -> person.surname },
            { person -> person.name }
        )
         */
    }


}
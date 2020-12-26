package dsl

import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main() {
    val scenarioBuilder = createScenario {
        employee {
            name = "Arbitrary employee name"
            hireDate = "2020-01-01"
        }
        absencePolicies {
            policy {
                name = "Arbitrary absence policy"
            }
            policy {
                name = "Another arbitrary policy"
            }
        }
    }

    val (employee, policies) = scenarioBuilder.build()

    println(employee)
    println(policies)
}

fun createScenario(block: ScenarioBuilder.() -> Unit) = ScenarioBuilder().apply(block)


data class Employee(
    val id: String,
    val name: String,
    val hireDate: LocalDate,
    val assignedPolicies: List<AbsencePolicyId>
)

data class AbsencePolicy(val id: AbsencePolicyId, val name: String)
typealias AbsencePolicyId = String

@DslMarker
annotation class ScenarioDSL

@ScenarioDSL
class ScenarioBuilder {
    private var employee: Employee? = null
    private var policies: List<AbsencePolicy> = listOf()

    fun employee(block: EmployeeBuilder.() -> Unit) {
        employee = EmployeeBuilder().apply(block).build()
    }

    fun absencePolicies(block: POLICIES.() -> Unit) {
        policies = POLICIES().apply(block)
    }

    fun build(): Pair<Employee, List<AbsencePolicy>> {
        if (employee == null) throw Exception()

        val newEmployee = Employee(
            id = employee!!.id,
            name = employee!!.name,
            hireDate = employee!!.hireDate,
            assignedPolicies = policies.map { policy -> policy.id }
        )

        return Pair(newEmployee, policies)
    }
}

class POLICIES : ArrayList<AbsencePolicy>() {
    fun policy(block: AbsencePolicyBuilder.() -> Unit) {
        add(AbsencePolicyBuilder().apply(block).build())
    }
}

@ScenarioDSL
class EmployeeBuilder {
    var name: String? = null
    var hireDate: String? = null

    fun build(): Employee {
        if (name == null || hireDate == null) {
            throw Exception()
        }

        return Employee(
            id = "arbitrary employee id",
            name = name!!,
            hireDate = LocalDate.parse(hireDate, DateTimeFormatter.ofPattern("yyyy-MM-d")),
            assignedPolicies = listOf()
        )
    }
}

@ScenarioDSL
class AbsencePolicyBuilder {
    var name: String? = null

    fun build(): AbsencePolicy {
        if (name == null) throw Exception()

        return AbsencePolicy(
            id = "${name!!} id",
            name = name!!
        )
    }
}
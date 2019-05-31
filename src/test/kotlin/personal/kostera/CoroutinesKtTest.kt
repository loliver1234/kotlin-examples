package personal.kostera

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

object CoroutinesKtTest : Spek({
    Feature("globalScopeCoroutineX functions") {
        val funList = listOf(::globalScopeCoroutine, ::globalScopeCoroutine2)
        funList.forEach {
            Scenario("sleep greater than delay") {
                val delaysMs = 100L
                val sleepMs = 200L
                val value1 = "g3vsdvsd"
                val value2 = "34fsdfd"
                Given("Function params: delaysMs=$delaysMs, sleepMs=$sleepMs, value1=$value1, value2=$value2") {}
                lateinit var result: String
                When("Executing function: ${it.name}") {
                    result = it.call(delaysMs, sleepMs, value1, value2)
                }
                Then("it should return correct value") {
                    assertEquals(value2 + value1, result, "Expected strings are not equal")
                }
            }
            Scenario("sleep lower than delay") {
                val delaysMs = 200L
                val sleepMs = 100L
                val value1 = "4bhdfab"
                val value2 = "zsdfn5"
                Given("Function params: delaysMs=$delaysMs, sleepMs=$sleepMs, value1=$value1, value2=$value2") {}
                lateinit var result: String
                When("Executing function: ${it.name}") {
                    result = it.call(delaysMs, sleepMs, value1, value2)
                }
                Then("it should return correct value") {
                    assertEquals(value2, result, "Expected strings are not equal")
                }
            }
        }
    }
    Feature("globalScopeWaitForJob function") {
        Scenario("result shoudl always be value2+value1") {
            val delaysMs = 100L
            val value1 = "g3vsdvsd"
            val value2 = "34fsdfd"
            Given("Function params: delaysMs=$delaysMs, value1=$value1, value2=$value2") {}
            lateinit var result: String
            When("Executing function: globalScopeWaitForJob") {
                result = globalScopeWaitForJob(delaysMs, value1, value2)
            }
            Then("it should return correct value") {
                assertEquals(value2 + value1, result, "Expected strings are not equal")
            }
        }
    }
    Feature("localScopeWithNestedCoroutineX function") {
        val funList = listOf(::localScopeWithNestedCoroutine, ::localScopeWithNestedCoroutine2)
        funList.forEach {
            Scenario("delayMs1 < delayMs2 < delayMs3") {
                val delaysMs1 = 100L
                val delaysMs2 = 200L
                val delaysMs3 = 300L
                Given("Function params: delaysMs1=$delaysMs1, delaysMs2=$delaysMs2, delaysMs3=$delaysMs3") {}
                lateinit var result: List<String>
                When("Executing function: ${it.name}") {
                    result = it.call(delaysMs1, delaysMs2, delaysMs3)
                }
                Then("it should return correct value") {
                    assertEquals(
                        listOf(
                            "runBlockingScopeLaunch",
                            "nestedCoroutineScopeLaunch",
                            "nestedCoroutineScope",
                            "runBlockingScope"
                        ), result, "Expected strings are not equal"
                    )
                }
            }
            Scenario("delayMs1 > delayMs2 > delayMs3") {
                val delaysMs1 = 300L
                val delaysMs2 = 200L
                val delaysMs3 = 100L
                Given("Function params: delaysMs1=$delaysMs1, delaysMs2=$delaysMs2, delaysMs3=$delaysMs3") {}
                lateinit var result: List<String>
                When("Executing function: ${it.name}") {
                    result = it.call(delaysMs1, delaysMs2, delaysMs3)
                }
                Then("it should return correct value") {
                    assertEquals(
                        listOf(
                            "nestedCoroutineScope",
                            "nestedCoroutineScopeLaunch",
                            "runBlockingScope",
                            "runBlockingScopeLaunch"
                        ), result, "Expected strings are not equal"
                    )
                }
            }
        }
    }
})
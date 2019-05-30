package personal.kostera

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

object FunctionAreFunKtTest : Spek({

    Feature("getWordsLongerThan5 function") {
        Scenario("should return only words longer than 5 chars") {
            val testList = listOf("Hello World!", "a", "b", "Hello World!!")
            Given("Test list: $testList") {}
            lateinit var result: List<String>
            When("Using function: getWordsLongerThan5($testList)") {
                result = getWordsLongerThan5(testList)
            }
            Then("it should return correct value") {
                assertEquals(listOf("Hello World!", "Hello World!!"), result, "Expected lists are not equal")
            }
        }
    }

    Feature("addSuffix extension function") {
        Scenario("should return string with added suffix") {
            val testString = "Hello World"
            Given("Test string: $testString") {}
            lateinit var result: String
            When("Using function: $testString.addSuffix(\"!\", 5)") {
                result = testString.addSuffix("!", 5)
            }
            Then("it should return correct value") {
                assertEquals("Hello World!!!!!", result, "Expected strings are not equal")
            }
        }
    }

    Feature("add infix function") {
        Scenario("should return string with added value") {
            val testString = "Hello"
            Given("Test string: $testString") {}
            lateinit var result: String
            When("Using infix function: $testString add \"World\"") {
                result = testString add "World"
            }
            Then("it should return correct value") {
                assertEquals("HelloWorld", result, "Expected strings are not equal")
            }
        }
    }
})
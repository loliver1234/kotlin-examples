package personal.kostera

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

object DeconstructingDeclarationsKtTest : Spek({
    Feature("multiply function") {
        Scenario("should return multiplication result") {
            val pair = Pair(10, 5)
            Given("Test pair: $pair") {}
            var result: Int = -1
            When("Using function: multiply($pair)") {
                result = multiply(pair)
            }
            Then("it should return correct value") {
                assertEquals(50, result, "Expected ints are not equal")
            }
        }
    }
})
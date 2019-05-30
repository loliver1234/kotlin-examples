package personal.kostera

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

object DelegatedPropertiesKtTest : Spek({
    Feature("custom delegated property") {
        Scenario("value not initialized") {
            val testSubject = ClassWithDelegatedProperties()
            Given("Test subject: $testSubject") {}
            lateinit var result: String
            When("Accessing property value testSubject.stringCustomDelegate") {
                result = testSubject.stringCustomDelegate
            }
            Then("it should return correct value") {
                assertEquals("Value was not assigned yet", result, "Expected strings are not equal")
            }
        }

        Scenario("value initialized") {
            val testSubject = ClassWithDelegatedProperties()
            val testString = "Hello World!"
            Given("Test subject: $testSubject") {
                testSubject.stringCustomDelegate = testString
            }
            lateinit var result: String
            When("Accessing property value testSubject.stringCustomDelegate") {
                result = testSubject.stringCustomDelegate
            }
            Then("it should return correct value") {
                assertEquals(testString, result, "Expected strings are not equal")
            }
        }

        Scenario("value initialized and reassigned") {
            val testSubject = ClassWithDelegatedProperties()
            val testString = "Hello World!"
            Given("Test subject: $testSubject") {
                testSubject.stringCustomDelegate = testString
            }
            When("Assigning new value to property testSubject.stringCustomDelegate") {
                testSubject.stringCustomDelegate = "New Hello World!"
            }
            Then("it should return correct value") {
                assertEquals("Zonk!", testSubject.stringCustomDelegate, "Expected strings are not equal")
            }
        }
    }

    Feature("lazy delegated property") {
        Scenario("value initialized") {
            val testSubject = ClassWithDelegatedProperties()
            val testString = "Hello World!"
            Given("Test subject: $testSubject") {
                testSubject.stringCustomDelegate = testString
            }
            lateinit var result: String
            When("Getting property testSubject.stringLazyDelegate") {
                result = testSubject.stringLazyDelegate
            }
            Then("it should return correct value") {
                assertEquals(testSubject.stringCustomDelegate, result, "Expected strings are not equal")
            }
        }

        Scenario("value initialized and reassigned") {
            val testSubject = ClassWithDelegatedProperties()
            val testString = "Hello World!"
            Given("Test subject: $testSubject") {
                testSubject.stringCustomDelegate = testString
            }
            lateinit var result: String
            When("Reassigning property testSubject.stringCustomDelegate and getting lazy value") {
                result = testSubject.stringLazyDelegate
                testSubject.stringCustomDelegate = "New Hello World!"
                result = testSubject.stringLazyDelegate
            }
            Then("it should return correct value") {
                assertEquals(testString, result, "Expected strings are not equal")
            }
        }
    }

    Feature("observable delegated property") {
        Scenario("observer values are stored") {
            val testSubject = ClassWithDelegatedProperties()
            val testStringInitial = "Hello World!"
            val testStringNew = "Hello New World!"
            Given("Test subject: $testSubject") {
                testSubject.stringObservableDelegate = testStringInitial
            }
            When("Reassigning property testSubject.stringObservableDelegate") {
                testSubject.stringObservableDelegate = testStringNew
            }
            Then("It should store both last and new values") {
                assertEquals(testStringInitial, testSubject.lastValue, "Expected strings are not equal")
                assertEquals(testStringNew, testSubject.newValue, "Expected strings are not equal")
            }
        }
    }

    Feature("vetoable delegated property") {
        Scenario("new value is vetoed") {
            val testSubject = ClassWithDelegatedProperties()
            val testStringInitial = "Hello World!"
            val testStringNew = "Hello"
            Given("Test subject: $testSubject") {
                testSubject.stringVetoableDelegate = testStringInitial
            }
            When("Reassigning property testSubject.stringVetoableDelegate") {
                testSubject.stringVetoableDelegate = testStringNew
            }
            Then("It should still have old value") {
                assertEquals(testStringInitial, testSubject.stringVetoableDelegate, "Expected strings are not equal")
            }
        }
        Scenario("new value is stored") {
            val testSubject = ClassWithDelegatedProperties()
            val testStringInitial = "Hello World!"
            val testStringNew = "Hello New Wolrd!"
            Given("Test subject: $testSubject") {
                testSubject.stringVetoableDelegate = testStringInitial
            }
            When("Reassigning property testSubject.stringVetoableDelegate") {
                testSubject.stringVetoableDelegate = testStringNew
            }
            Then("It should still have old value") {
                assertEquals(testStringNew, testSubject.stringVetoableDelegate, "Expected strings are not equal")
            }
        }
    }

    Feature("notNull delegated property") {
        Scenario("null value is accessed") {
            val testSubject = ClassWithDelegatedProperties()
            Given("Test subject: $testSubject") {}
            lateinit var result: Exception
            When("Accessing property testSubject.stringNotNullDelegate") {
                result = assertFailsWith(IllegalStateException::class) { testSubject.stringNotNullDelegate }
            }
            Then("It should throw ISE") {
                assertTrue(result is IllegalStateException)
            }
        }
    }

    Feature("map delegated property") {
        Scenario("properties are initialized with map") {
            lateinit var testSubject: MapDelegatedClass
            val map = mapOf(
                "name" to "John Doe",
                "age" to 25
            )
            Given("Test map: $map") {}
            When("Initializing object with a map") {
                testSubject = MapDelegatedClass(map)
            }
            Then("It should have all fields assigned") {
                assertEquals("John Doe", testSubject.name, "Property values are not equal")
                assertEquals(25, testSubject.age, "Property values are not equal")
            }
        }
    }
})
package personal.kostera

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

object BasicSyntaxKtTest : Spek({

    Feature("stringTemplatePlainObject function") {
        Scenario("should return properly injected string") {
            val testString = "Hello World!"
            Given("Test string: $testString") {}
            lateinit var result: String
            When("Using string template function: stringTemplatePlainObject($testString)") {
                result = stringTemplatePlainObject(testString)
            }
            Then("it should return correct value") {
                assertEquals("Your value is: Hello World!", result, "Expected strings are not equal")
            }
        }
    }

    Feature("stringTemplateExpression function") {
        Scenario("should return properly injected expression") {
            val testString = "Hello World!"
            Given("Test string: $testString") {}
            lateinit var result: String
            When("Using string template function: stringTemplateExpression($testString)") {
                result = stringTemplateExpression(testString)
            }
            Then("it should return correct value") {
                assertEquals("Your string length: 12", result, "Expected strings are not equal")
            }
        }
    }

    Feature("forLoop function") {
        Scenario("should return properly concatenated String") {
            val testList = listOf("Hello", " ", "World!")
            Given("Test list: $testList") {}
            lateinit var result: String
            When("Using forLoop function: forLoop($testList)") {
                result = forLoop(testList)
            }
            Then("it should return correct value") {
                assertEquals("Hello World!", result, "Expected strings are not equal")
            }
        }
    }

    Feature("whenStatement function") {
        Scenario("should return proper EN String") {
            val testLang = "EN"
            Given("Test lang: $testLang") {}
            lateinit var result: String
            When("Using whenStatement function: whenStatement($testLang)") {
                result = whenStatement(testLang)
            }
            Then("it should return correct value") {
                assertEquals("Hello World!", result, "Expected strings are not equal")
            }
        }
        Scenario("should return proper ES String") {
            val testLang = "ES"
            Given("Test lang: $testLang") {}
            lateinit var result: String
            When("Using whenStatement function: whenStatement($testLang)") {
                result = whenStatement(testLang)
            }
            Then("it should return correct value") {
                assertEquals("Hola Mundo!", result, "Expected strings are not equal")
            }
        }
        Scenario("should return proper IT String") {
            val testLang = "IT"
            Given("Test lang: $testLang") {}
            lateinit var result: String
            When("Using whenStatement function: whenStatement($testLang)") {
                result = whenStatement(testLang)
            }
            Then("it should return correct value") {
                assertEquals("Ciao mondo!", result, "Expected strings are not equal")
            }
        }
        Scenario("should return proper Error String") {
            val testLang = "UNKNOWN"
            Given("Test lang: $testLang") {}
            lateinit var result: String
            When("Using whenStatement function: whenStatement($testLang)") {
                result = whenStatement(testLang)
            }
            Then("it should return correct value") {
                assertEquals("I am error.", result, "Expected strings are not equal")
            }
        }
    }

    Feature("inlineIfValueAssignment function") {
        Scenario("string longer than 5") {
            val testString = "Hello World!"
            Given("String of length: ${testString.length}") {}
            lateinit var result: String
            When("Using inlineIfValueAssignment function: inlineIfValueAssignment($testString)") {
                result = inlineIfValueAssignment(testString)
            }
            Then("it should return correct value") {
                assertEquals("Hello World!", result, "Expected strings are not equal")
            }
        }
        Scenario("string shorter than 5") {
            val testString = "Hell"
            Given("String of length: ${testString.length}") {}
            lateinit var result: String
            When("Using inlineIfValueAssignment function: inlineIfValueAssignment($testString)") {
                result = inlineIfValueAssignment(testString)
            }
            Then("it should return correct value") {
                assertEquals("Hello World!", result, "Expected strings are not equal")
            }
        }
    }

    Feature("max function") {
        Scenario("different values") {
            val testValue1 = 10
            val testValue2 = 20
            Given("Test values: $testValue1 and $testValue2") {}
            var result: Int = 0
            When("Using max function: max($testValue1, $testValue2)") {
                result = max(testValue1, testValue2)
            }
            Then("it should return correct value") {
                assertEquals(20, result, "Expected ints are not equal")
            }
        }
    }

    Feature("parseInt function") {
        Scenario("passed integer string") {
            val testValue1 = "10"
            Given("Test value: $testValue1") {}
            var result: Int? = 0
            When("Using parseInt function: parseInt($testValue1)") {
                result = parseInt(testValue1)
            }
            Then("it should return correct value") {
                assertEquals(10, result, "Expected ints are not equal")
            }
        }
        Scenario("passed non-integer string") {
            val testValue1 = "Hello World!"
            Given("Test value: $testValue1") {}
            var result: Int? = 0
            When("Using parseInt function: parseInt($testValue1)") {
                result = parseInt(testValue1)
            }
            Then("it should return null") {
                assertNull(result, "Expected result is not null")
            }
        }
    }

    Feature("multiplyValues function") {
        Scenario("passed integer strings") {
            val testValue1 = "10"
            val testValue2 = "20"
            Given("Test values: $testValue1, $testValue2") {}
            var result: Int? = 0
            When("Using multiplyValues function: multiplyValues($testValue1, $testValue2)") {
                result = multiplyValues(testValue1, testValue2)
            }
            Then("it should return correct value") {
                assertEquals(200, result, "Expected ints are not equal")
            }
        }
        Scenario("passed non-integer strings") {
            val testValue1 = "Hello World!"
            val testValue2 = "20"
            Given("Test values: $testValue1, $testValue2") {}
            var result: Int? = 0
            When("Using multiplyValues function: multiplyValues($testValue1, $testValue2)") {
                result = multiplyValues(testValue1, testValue2)
            }
            Then("it should return null") {
                assertNull(result, "Expected result is not null")
            }
        }
    }

    Feature("getLengthSafeCallOperator function") {
        Scenario("should return correct length") {
            val testString = "Hello World!"
            Given("Test string: $testString") {}
            var result: Int? = null
            When("Using function: getLengthSafeCallOperator($testString)") {
                result = getLengthSafeCallOperator(testString)
            }
            Then("it should return correct length") {
                assertEquals(12, result, "Expected ints are not equal")
            }
        }
        Scenario("should return null") {
            Given("Test value: null") {}
            var result: Int? = 10
            When("Using function: getLengthSafeCallOperator(null)") {
                result = getLengthSafeCallOperator(null)
            }
            Then("it should return null") {
                assertNull(result, "Expected result is not null")
            }
        }
    }

    Feature("getLengthSafeCallOperatorNested function") {
        Scenario("should return null") {
            val testObject = OuterClass(InnerClass(null))
            Given("Test object: $testObject") {}
            var result: Int? = 10
            When("Using function: getLengthSafeCallOperatorNested($testObject)") {
                result = getLengthSafeCallOperatorNested(testObject)
            }
            Then("it should return null") {
                assertNull(result, "Expected result is not null")
            }
        }
        Scenario("should return correct length") {
            val testObject = OuterClass(InnerClass("Hello World!"))
            Given("Test object: $testObject") {}
            var result: Int? = 10
            When("Using function: getLengthSafeCallOperatorNested($testObject)") {
                result = getLengthSafeCallOperatorNested(testObject)
            }
            Then("it should return correct length") {
                assertEquals(12, result, "Expected ints are not equal")
            }
        }
    }

    Feature("getLengthElvisOperator function") {
        Scenario("should return -1") {
            val testObject = OuterClass(InnerClass(null))
            Given("Test object: $testObject") {}
            var result: Int? = 10
            When("Using function: getLengthSafeCallOperatorNested($testObject)") {
                result = getLengthElvisOperator(testObject)
            }
            Then("it should return -1") {
                assertEquals(-1, result, "Expected ints are not equal")
            }
        }
        Scenario("should return correct length") {
            val testObject = OuterClass(InnerClass("Hello World!"))
            Given("Test object: $testObject") {}
            var result: Int? = 10
            When("Using function: getLengthSafeCallOperatorNested($testObject)") {
                result = getLengthElvisOperator(testObject)
            }
            Then("it should return correct length") {
                assertEquals(12, result, "Expected ints are not equal")
            }
        }
    }

    Feature("getListWithoutNulls function") {
        Scenario("should return list without null values") {
            val testList = listOf("Hello", null, "World!")
            Given("Test list: $testList") {}
            lateinit var result: List<Any>
            When("Using function: getListWithoutNulls($testList)") {
                result = getListWithoutNulls(testList)
            }
            Then("it should return correct value") {
                assertEquals(listOf("Hello", "World!"), result, "Expected lists are not equal")
            }
        }
    }

    Feature("getListWithoutNullsKotlinFilter function") {
        Scenario("should return list without null values") {
            val testList = listOf("Hello", null, "World!")
            Given("Test list: $testList") {}
            lateinit var result: List<Any>
            When("Using function: getListWithoutNullsKotlinFilter($testList)") {
                result = getListWithoutNullsKotlinFilter(testList)
            }
            Then("it should return correct value") {
                assertEquals(listOf("Hello", "World!"), result, "Expected lists are not equal")
            }
        }
    }

    Feature("throwsNPEIfNull function") {
        Scenario("should return length") {
            val testString = "Hello World!"
            Given("Test string: $testString") {}
            var result: Int = -1
            When("Using function: throwsNPEIfNull($testString)") {
                result = throwsNPEIfNull(testString)
            }
            Then("it should return correct value") {
                assertEquals(12, result, "Expected ints are not equal")
            }
        }
        Scenario("should throw NPE") {
            val testString = null
            Given("Test string: $testString") {}
            lateinit var result: Exception
            When("Using function: throwsNPEIfNull($testString)") {
                result = assertFailsWith(NullPointerException::class) {
                    throwsNPEIfNull(testString)
                }
            }
            Then("it should throw NPE") {
                assertTrue(result is java.lang.NullPointerException)
            }
        }
    }

    Feature("parseIntSafeCastOperator function") {
        Scenario("should return null") {
            val testString = "10"
            Given("Test string: $testString") {}
            var result: Int? = null
            When("Using function: parseIntSafeCastOperator($testString)") {
                result = parseIntSafeCastOperator(testString)
            }
            Then("it should return null") {
                assertNull(result, "Expected result is not null")
            }
        }
        Scenario("should return Int") {
            val testInt = 20
            Given("Test int: $testInt") {}
            var result: Int? = null
            When("Using function: parseIntSafeCastOperator($testInt)") {
                result = parseIntSafeCastOperator(testInt)
            }
            Then("it should return correct value") {
                assertEquals(testInt, result, "Expected ints are not equal")
            }
        }
    }
})
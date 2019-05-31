package personal.kostera

data class InnerClass(val value: String?)

data class OuterClass(var innerClass: InnerClass?)

// String template using $val
fun stringTemplatePlainObject(value: String): String {
    return "Your value is: $value"
}

// String template using ${expresion}
fun stringTemplateExpression(value: String): String {
    return "Your string length: ${value.length}"
}


// For loop with Collections and in syntax
fun forLoop(list: List<String>): String {
    var result = ""
    for (string in list) {
        result += string;
    }
    return result
}

// When statement
fun whenStatement(language: String): String {
    return when (language) {
        "EN" -> "Hello World!"
        "ES" -> "Hola Mundo!"
        "IT" -> "Ciao mondo!"
        else -> "I am error."
    }
}

// Inline if value assignment
fun inlineIfValueAssignment(value: String): String {
    val helloWorldString = if (value.length > 5) value else "Hello World!"
    return helloWorldString
}

// Inline if value assignment to function
fun max(firstValue: Int, secondValue: Int) = if (firstValue > secondValue) firstValue else secondValue

// Nullable return value
fun parseInt(value: String): Int? {
    try {
        return value.toInt()
    } catch (e: NumberFormatException) {
        // nothing
    }
    return null
}

fun multiplyValues(firstValue: String, secondValue: String): Int? {
    val x = parseInt(firstValue)
    val y = parseInt(secondValue)
    // compiler won't allow nullable vals multiplication
    // println(x * y)
    if (x != null && y != null) {
        return (x * y)
    }
    return null
}

// Safe call operator ?.
fun getLengthSafeCallOperator(value: String?): Int? {
    return value?.length
}

// Safe call operator ?.
fun getLengthSafeCallOperatorNested(outerClass: OuterClass?): Int? {
    return outerClass?.innerClass?.value?.length
}

// Elvis Operator :?
fun getLengthElvisOperator(outerClass: OuterClass?): Int {
    return outerClass?.innerClass?.value?.length ?: -1
}

// Safe call operator with let
fun getListWithoutNulls(list: List<Any?>): List<Any> {
    val resultList = ArrayList<Any>()
    for (item in list) {
        // This is only executed if item is not null
        item?.let { resultList.add(it) }
    }
    return resultList
}

// Same logic with kotlin built in function
fun getListWithoutNullsKotlinFilter(list: List<Any?>) = list.filterNotNull()

// NPE-lovers: the not-null assertion operator
fun throwsNPEIfNull(value: String?): Int {
    return value!!.length
}

// Safe cast operator as?
fun parseIntSafeCastOperator(value: Any): Int? {
    return value as? Int
}

// object keyword creates a singleton instance
// object declarations are initialized lazily, when accessed for the first time
// They don't have constructors, but values can be initialized with init {} block
object Singleton {
    lateinit var value: String

    init {
        value = "init value"
    }
}

fun objectReference() {
    println(Singleton.value)
    doSomeSneakyStuff()
    println(Singleton.value)
}

fun doSomeSneakyStuff() {
    Singleton.value = "zonk"
}
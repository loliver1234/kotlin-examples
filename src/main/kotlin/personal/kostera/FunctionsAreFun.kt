package personal.kostera

// Function Composition

fun isLongerThan5(length: Int) = length > 5
fun length(value: String) = value.length
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

// In this example
// fun <String, Int, Boolean> compose(f: (Int) -> Boolean, g: (String) -> Int): (String) -> Boolean
fun getWordsLongerThan5(list: List<String>): List<String> {
    val composedFun = compose(::isLongerThan5, ::length)
    return list.filter(composedFun)
}

// Function Extensions

// from now on addSuffix method will be available for String objects, e.g. "Hello World".addSuffix("!", 5)
fun String.addSuffix(suffix: String, count: Int): String {
    var result = this
    for (i in 1..count) {
        result += suffix
    }
    return result
}

// Infix Functions

// from now on add method can be used like this: val helloWorld = "Hello" add "World"
infix fun String.add(value: String): String {
    return this + value
}
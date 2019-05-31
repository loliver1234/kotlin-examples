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

// Inline Functions
// inline keyword
// IntelliJ warns about when to use inline functions

// IntelliJ should warn that:
// Expected performance impact of inlining '...' is insignificant. Inlining works best for functions with parameters of functional types

// Few guidelines how inline shoudl be used:
// You can inline when functional type param is called directly or passed to other inline function
// You cannot inline when function parameter is being assigned to variable inside function
// You should consider inlining if at least one of your functional typed parameters can be inlined, use noinline for the rest.
// You should not inline huge functions, think about generated byte code. It will be copied to all places the function is called from.
inline fun doNothing() {}

// You cannot inline when function parameter is being assigned to variable inside function
//inline fun wrapFunction(function: () -> Unit) {
//    val myFunction = function
//}

// You can do that with noinline keyword but it doesn't make sense to do that for the only parameter passed
// InteliJ should also warn about this
inline fun wrapFunction(noinline function: () -> Unit) {
    val myFunction = function
}

// You can inline when functional type param is called directly or passed to other inline function
inline fun wrapFunctionCorrect(function: () -> Unit) {
    doNothing()
    function()
    doNothing()
}

// You should consider inlining if at least one of your functional typed parameters can be inlined, use noinline for the rest.
inline fun wrapFunctionCorrect(function: () -> Unit, noinline otherFunction: () -> Unit) {
    doNothing()
    val x = otherFunction
    function()
    doNothing()
}
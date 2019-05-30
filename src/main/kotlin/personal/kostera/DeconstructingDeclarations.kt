package personal.kostera

data class Pair(val firstValue: Int, val secondValue: Int)

fun multiply(pair: Pair): Int {
    // Deconstructed Pair object into 2 values
    val (multiplicand, multiplier) = pair
    return multiplicand * multiplier
}
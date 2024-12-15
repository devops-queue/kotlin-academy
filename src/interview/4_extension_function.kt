fun main() {
  data class RationalNumber(val numerator: Int, val denominator: Int)

  fun Int.r(): RationalNumber = RationalNumber (this, 1)

  fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(first, second)

  println(5.r())

  println((4 to 5).r())
}
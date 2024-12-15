fun main() {
  data class RationalNumber(val numerator: Int, val denominator: Int) : Comparable<RationalNumber> {
    override fun compareTo(other: RationalNumber): Int {
      // Cross-multiplication to compare without floating-point division
      return (this.numerator * other.denominator) - (other.numerator * this.denominator)
    }

    operator fun plus(other: RationalNumber): RationalNumber {
      val newNumerator = this.numerator * other.denominator + other.numerator * this.denominator
      val newDenominator = this.denominator * other.denominator
      return RationalNumber(newNumerator, newDenominator)
    }

    operator fun minus(other: RationalNumber): RationalNumber {
      val newNumerator = this.numerator * other.denominator - other.numerator * this.denominator
      val newDenominator = this.denominator * other.denominator
      return RationalNumber(newNumerator, newDenominator)
    }

    operator fun times(other: RationalNumber): RationalNumber {
      return RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator)
    }

    operator fun div(other: RationalNumber): RationalNumber {
      return RationalNumber(this.numerator * other.denominator, this.denominator * other.numerator)
    }

    operator fun unaryMinus(): RationalNumber {
      return RationalNumber(-this.numerator, this.denominator)
    }
  }

  data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
      return (this.year * 10000 + this.month * 100 + this.dayOfMonth) -
      (other.year * 10000 + other.month * 100 + other.dayOfMonth)
    }
  }

    fun test(date1: MyDate, date2: MyDate) {
      // this code should compile:
      println(date1 < date2)
    }

  println(test(MyDate(2002, 3, 23), MyDate(2033,4,34)))
}
fun main() {
  val seq = (1..100).asSequence()

  val result = seq.joinToString(", ") {
    when {
      it % 15 == 0 -> "FizzBuzz"
      it % 5 == 0 -> "Buzz"
      it % 3 == 0 -> "Fizz"
      else -> "$it"
    }
  }
  print(result)
}

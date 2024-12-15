fun main() {
  fun fib(n: Int) : Int {
    return when {
      n < 2 -> n
      else -> fib(n-1) + fib(n-2)
      }
  }



  // Iterative Fibonacci sequence generator
  fun fibonacciSequence(): Sequence<Int> = sequence {
    var a = 0
    var b = 1

    yield(a)
    yield(b)
    while (true) {
      val next = a + b
      yield(next)
      a = b
      b = next
    }
  }

// Generate and print the first 20 Fibonacci numbers
val fibSequenceFast = fibonacciSequence()

  println(fibSequenceFast.take(40).toList())



  val fibSequence = generateSequence(0) { it+1 }.map {fib(it)}
  println(fibSequence.take(40).toList())


  // Memoized Fibonacci function
  val memo = mutableMapOf<Int, Int>()
  fun fibMem(n: Int): Int {
    if (n < 2) return n
    return memo.getOrPut(n) { fibMem(n - 1) + fibMem(n - 2) }
  }

// Generate Fibonacci sequence using memoization
  val fibSequenceM = generateSequence(0) { it + 1 }.map { fib(it) }
  println(fibSequenceM.take(20).toList())


}
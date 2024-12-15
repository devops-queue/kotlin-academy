package interview
fun main () {
  /* prime number is to form from sequence seed = 2 fetch the first item starting from 2
     and filter out first item which can be % with zero
     recursively perform the above
  */

  fun isPrime(num: Int): Boolean {
    if (num < 2) { return false }
    for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
      if (num % i == 0) return false
    }
    return true
  }

  val infiniteSeq = generateSequence(2) {it + 1}

  val primeList = infiniteSeq.filter { isPrime(it)}

  //println(primeList.take(100).toList())

  println("Use Sieve on Sequence")

  // laziness will be effiective always,
  // yield and yieldAll:
  // These functions are part of Kotlin's coroutine framework
  // and are used within a sequence builder to produce values lazily.
  // When you use yield, it produces a single value and pauses
  // until the next value is requested.
  // yieldAll allows you to delegate the generation of a sequence to another sequence,
  // again producing values lazily as needed.
  fun sieve(seq: Sequence<Int>): Sequence<Int> {
    val firstElement = seq.first()
    return sequence {
      yield(firstElement)
      yieldAll(sieve(seq.drop(1).filter { it % firstElement != 0}))
    }
  }

  val f100Primes = sieve(infiniteSeq).take(100).toList()
  println(f100Primes)
// allow null check on sequence null element
  fun sieveNull(seq: Sequence<Int?>): Sequence<Int> = sequence {
    val firstElement = seq.firstOrNull() ?: return@sequence
    yield(firstElement)
    yieldAll(sieveNull(seq.drop(1).filter { it != null && it % firstElement != 0 }))
  }
  // Generate and print the first 100 prime numbers
  val first100Primes = sieveNull(infiniteSeq).take(100).toList()
  println(first100Primes)


  // Generate the special sequence with [ 2, null, 3, null, ... 100, null]
  val specialInfiniteSeq = generateSequence(2) { it + 1 } .flatMap { sequenceOf(it, null) }
  // Convert to a list to verify the result
  println(specialInfiniteSeq.take(100).toList())
  val specialPrimeList = sieveNull(specialInfiniteSeq).take(100).toList()
  // Print the special list
  println(specialPrimeList)


}
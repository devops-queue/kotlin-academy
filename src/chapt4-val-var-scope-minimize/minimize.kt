import java.awt.Color

data class User(val name: String)

fun main() {
  /* Using local variables instead of properties.
  Using variables in the narrowest scope possible, for instance by defining a variable inside a loop if it is used only inside this loop.
   */
  val users = mutableListOf(User("a"), User("b"), User("C"))

  users.add(User("4"))

  // Better
  for (i in users.indices) {
    val user = users[i]
    println("User at $i is $user")
  }

  println()
  // Same variables scope, nicer syntax
  for ((i, user) in users.withIndex()) {
    println("User at $i is $user")
  }

  //Whether a variable is read-only or read-write, we always prefer it to be initialized
// when it is defined.

  // If we need to set up multiple properties, destructuring declarations can help us:
  // val clause together with initialization or assignment and type casting
  fun updateWeather(degrees: Int) {
    val (description, color) = when {
      degrees < 5 -> "cold" to Color.BLUE
      degrees < 23 -> "mild" to Color.YELLOW
      else -> "hot" to Color.RED
    }
    println("$description, $color")
    // ...
  }
  updateWeather(10)

  // sieve of Erathosthenes

  val primes: Sequence<Int> = sequence {
    var numbers = generateSequence(2) { it + 1 }
    while (true) {
      val prime = numbers.first()
      yield(prime)
      numbers = numbers.drop(1)
        .filter{ it % prime != 0}
    }
  }

  //println(primes.take(1000).toList())
  // find out count of the first prime >7000
  val first1000prime = primes.take(1000).toList()

  for ((i, prime) in first1000prime.withIndex()) {
    when {
      prime > 7000 -> {println("first item > 7000 is $prime @location $($i+1)"); break}
    }
  }

}
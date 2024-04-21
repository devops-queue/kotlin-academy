import java.io.File

// Ref: https://blog.kotlin-academy.com/effective-kotlin-use-sequence-for-bigger-collections-with-more-than-one-processing-step-649a15bb4bf
fun main() {
    /* sequence are lazy
    - They keep the natural order of operations
    - They do a minimal number of operations
    - They can be infinite
    - They do not need to create collections at every step
    */
    val seq = sequenceOf(1,2,3)
    val filtered = seq.filter { print("f$it "); it % 2 == 1 }
    println(filtered)  // FilteringSequence@...
    val asList = filtered.toList() // f1 f2 f3 extra last step for calculation
    println(asList) // [1, 3]
    val list = listOf(1,2,3)
    val listFiltered = list.filter { print("f$it "); it % 2 == 1 } // f1 f2 f3, list perform calculation
    println(listFiltered) // [1, 3]

    // order is important lazy sequence vs eager order
    println("Sequence order: in logic order")
    sequenceOf(1,2,3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .forEach { print("E$it, ") } // Prints: F1, M1, E2, F2, F3, M3, E6,

    println()

    println("iterable order in rush step order:")
    listOf(1,2,3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .forEach { print("E$it, ") } // Prints: F1, F2, F3, M1, M3, E2, E6,
    println()

    println("less operation and natural with basic loop for optimization:")
    (1..10).asSequence()
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .find { it > 5 }  // Prints: F1, M1, F2, F3, M3,
    println()

    println("iterable all steps, eager order")
    (1..10)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .find { it > 5 }  // Prints: F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, M1, M3, M5, M7, M9,
    println()

    // Examples of such operations are first, find, take, any, all, none or indexOf.
    // sequence can be infinite
    println("infinte sequence with take and generate:")

    // suspending function (coroutine) that generates the next number on demand
    generateSequence(1) { it + 1 }
        .map { it * 2 }
        .take(10)
        .forEach { print("$it, ") }  // Prints: 2, 4, 6, 8, 10, 12, 14, 16, 18, 20,


    println()
    println("fibonacci using yield function and while loop")
    val fibonacci = sequence {
        yield(1)
        var current = 1
        var prev = 1
        while (true) {
            yield(current)
            val temp = prev
            prev = current
            current += temp
        }
    }
    print(fibonacci.take(20).toList()) // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]

    println()

    println("use as sequence to avoid big collection generation")
    val numbers = intArrayOf(20, 50, 42, 90, 1, 100, 123)

    val sumfromcollection = numbers
        .filter { it % 10 == 0 } // 1 collection here
        .map { it * 2 } // 1 collection here
        .sum() // In total, 2 collections created under the hood
    println("sum from collection: $sumfromcollection")


    val sumfromsequence = numbers
        .asSequence()
        .filter { it % 10 == 0 }
        .map { it * 2 }
        .sum()  // No collections created
    println("sum from sequence: $sumfromsequence")


    // readlin bad solution use collection perline
    // BAD SOLUTION, DO NOT USE COLLECTIONS FOR
// POSSIBLY BIG FILES
//    File("ChicagoCrimes.csv").readLines()
//        .drop(1) // Drop descriptions of the columns
//        .mapNotNull { it.split(",").getOrNull(6) }
//        // Find description
//        .filter { "CANNABIS" in it }
//        .count()
//        .let(::println)

    // sequences for bigger files is not only for memory but also for performance.
//    File("ChicagoCrimes.csv").useLines { lines ->
//        // The type of `lines` is Sequence<String>
//        lines
//            .drop(1) // Drop descriptions of the columns
//            .mapNotNull { it.split(",").getOrNull(6) }
//            // Find description
//            .filter { "CANNABIS" in it }
//            .count()
//            .let { println(it) } // 318185


    //samples for 3 steps processing filter -> map -> calculation -> tolist
    class Product(
        val name: String,
        val bought: Boolean,
        val quantity: Int,
        val price: Double
    )

    val product_a = Product("a", true, 4, 6.6)
    val product_b = Product("b", false, 2, 6.9)

    val productsList = arrayListOf(product_a, product_b)
    fun twoStepListProcessing(): List<Double> {
        return productsList
            .filter { it.bought }
            .map { it.price }
    }

    fun twoStepSequenceProcessing(): List<Double> {
        return productsList.asSequence()
            .filter { it.bought }
            .map { it.price }
            .toList()
    }

    fun threeStepListProcessing(): Double {
        return productsList
            .filter { it.bought }
            .map { it.price }
            .average()
    }

    fun threeStepSequenceProcessing(): Double {
        return productsList.asSequence()
            .filter { it.bought }
            .map { it.price*it.quantity }
            .average()
    }
    println(threeStepSequenceProcessing())

    //sorted care on infinite list
    //generateSequence(0) { it + 1 }.take(10).sorted().toList()
    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    //generateSequence(0) { it + 1 }.sorted().take(10).toList()
    // Infinite time. Does not return.
}


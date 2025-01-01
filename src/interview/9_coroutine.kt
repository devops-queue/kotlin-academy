package interview
import kotlinx.coroutines.*

fun main() {
  runBlocking {
    try {
      val result = asyncOperation()
      println(result)
    } catch (e: Exception) {
      println("Error: ${e.message}")
    }
  }
}

suspend fun asyncOperation(): String {
  return withContext(Dispatchers.Default) {
    delay(2000L) // Simulating a 2-second delay
    val success = Math.random() > 0.5 // Simulating success or failure

    if (success) {
      "Operation was successful"
    } else {
      throw Exception("Operation failed")
    }
  }
}

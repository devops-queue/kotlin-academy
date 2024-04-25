import kotlin.concurrent.thread

//use getter to get different val from a val properties
var name: String = "Dan"
var surname: String = "tse"
val fullName
  get() = "$name $surname"

var list = listOf<Int>()

fun main() {
  //Notice though that read-only properties are not necessarily immutable or final.
  // A read-only property can hold a mutable object:

  var list = mutableListOf(1, 2, 3)
  list.add(4)

  print(list) // [1, 2, 3, 4]


  println(fullName) // Dan tse
  name = "feng"
  println(fullName) // feng tse

// mutablility cause muti-thread state not predictable

  for (i in 1..1000) {
    thread {
      Thread.sleep(10)
      list += i
    }
  }
  Thread.sleep(1000)
  print(list.size) // Very unlikely to be 1000
  println(list)


}
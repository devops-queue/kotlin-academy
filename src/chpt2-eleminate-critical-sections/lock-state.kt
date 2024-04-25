import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

// Whenever you have a shared state that might be modified by multiple threads,
// you need to ensure that all the operations on this state are executed correctly.

// Synchronization in Kotlin/JVM

class Counter {
  private val lock = Any()
  private var num = 0

  fun inc() = synchronized(lock) {
    num += 1
  }

  fun dec() = synchronized(lock) {
    num -= 1
  }

  // Synchronization is not necessary; however,
  // without it, getter might serve stale value
  fun get(): Int = num
}
fun main() {

  val lock = Any()
  var num = 0
  for (i in 1..1000) {
    thread {
      Thread.sleep(10)
      synchronized(lock) {
        num += 1
      }
    }
  }
  Thread.sleep(1000)
  println(num) // 1000

  val count = Counter()
  for (i in 1..1000) {
    count.inc()
  }
  println(count.get())

  for (i in 1..1000) {
    count.dec()
  }
  println(count.get())

  //
  // Atomic objects
  // AtomicInteger, AtomicLong, AtomicBoolean, AtomicReference, and many more.
// Each of these offers methods that are guaranteed to be executed atomically.
// For example, AtomicInteger offers an incrementAndGet method that increments a value and returns the new value. The example below shows how to use AtomicInteger to increment a variable correctly.
  //
  //Marcin Moskała. effectivekotlin (p. 47). Kindle Edition.

  val atomic_num = AtomicInteger(0)
  for (i in 1..1000) {
    thread {
      //Thread.sleep(10)
      atomic_num.incrementAndGet()
    }
  }
  Thread.sleep(1000)
  println(atomic_num.get()) // 1000

  // Concurrent collections
  //
  //Marcin Moskała. effectivekotlin (p. 48). Kindle Edition.

  val map = ConcurrentHashMap<Int, String>()
  for (i in 1..4) {
    thread {
      Thread.sleep(1)
      map.put(i, "E$i")
    }
    thread {
      Thread.sleep(10)
      println(map.toList().sumOf { it.first })
    }
  }

  // concurrent hash newkeyset
  val set = ConcurrentHashMap.newKeySet<Int>()
  for (i in 1..10) {
    thread {
      Thread.sleep(1)
      set += i
    }
  }
  Thread.sleep(50)
  println(set.size)
  println(set.toList())

  // Using AtomicFU
//  val num3 = atomic(0)
//  for (i in 1..10) {
//    thread {
//      Thread.sleep(10)
//      num3.incrementAndGet()
//    }
//  }
//  Thread.sleep(5000)
//  print(num3.value) // 1000

  // protect the element states with list read only or defensive copying of object with synchronized lock when modification
  data class User(val name: String)

  class UserRepository {
    private var users: List<User> = listOf()
    private val lock = Any()

    fun loadAll(): List<User> = users

    fun add(user: User) = synchronized(lock) {
      users = users + user
    }
  }

  val repo = UserRepository()
  thread {
    for (i in 1..10) repo.add(User("User$i"))
  }
  thread {
    for (i in 1..10) {
      val list = repo.loadAll()
      for (e in list) {
        Thread.sleep(20)
        println(e)
      }
    }
  }
}
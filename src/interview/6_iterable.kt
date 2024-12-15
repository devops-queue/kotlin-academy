//operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

import interview.utils.MyDate

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, callback: (MyDate) -> Unit) : Int {
  var count = 0
  for (date in firstDate..secondDate) {
    callback(date)
    count ++
  }
  return count
}

fun main() {
  val firstDate = MyDate(2024, 12, 4)
  val secondDate = MyDate(2025, 1, 1)
  val totalDays = iterateOverDateRange(firstDate, secondDate) { println(it) }
  println("Total Days: $totalDays")
}

import interview.utils.MyDate
import interview.utils.TimeInterval
import interview.utils.TimeInterval.*

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(repeated: RepeatedTimeInterval): MyDate {
  return this.addTimeIntervals(repeated.timeInterval, repeated.number)
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

  // Function to add time intervals to MyDate

fun main() {
  fun task1(today: MyDate): MyDate {
    return today + YEAR + WEEK
  }

  fun task2(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
    //return today + YEAR * 2 + WEEK * 3 + DAY * 5
  }

  println(task1(MyDate(2024, 12, 6)))

  println(task2(MyDate(2024, 12, 6)))

}

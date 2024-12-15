package interview.utils

import interview.utils.TimeInterval
import java.time.LocalDate

data class MyDate(val year: Int, val month: Int, val day: Int) {
  // Method to get the next date
  fun toLocalDate(): LocalDate {
    return LocalDate.of(year, month, day)
  }

  companion object {
    fun fromLocalDate(date: LocalDate): MyDate {
      return MyDate(date.year, date.monthValue, date.dayOfMonth)
    }
  }

  fun followingDate(): MyDate {
    val date = LocalDate.of(year, month, day).plusDays(1)
    return MyDate(date.year, date.monthValue, date.dayOfMonth)
  }
  // Function to add time intervals to MyDate
  fun addTimeIntervals(timeInterval: TimeInterval, number: Int): MyDate {
    val localDate = this.toLocalDate()
    val newDate = when (timeInterval) {
      TimeInterval.DAY -> localDate.plusDays(number.toLong())
      TimeInterval.WEEK -> localDate.plusWeeks(number.toLong())
      TimeInterval.YEAR -> localDate.plusYears(number.toLong())
    }
    return MyDate.fromLocalDate(newDate)
  }

  operator fun compareTo(other: MyDate): Int {
    return when {
      this.year != other.year -> this.year - other.year
      this.month != other.month -> this.month - other.month
      else -> this.day - other.day
    }
  }

  operator fun rangeTo(other: MyDate): DateRange = DateRange(this, other)
}

class DateRange(val start: MyDate, val end: MyDate) : Iterable<MyDate> {
  override fun iterator(): Iterator<MyDate> {
    return object : Iterator<MyDate> {
      var current = start

      override fun hasNext(): Boolean = current <= end

      override fun next(): MyDate {
        if (!hasNext()) throw NoSuchElementException()
        val next = current
        current = current.followingDate()
        return next
      }
    }
  }
}


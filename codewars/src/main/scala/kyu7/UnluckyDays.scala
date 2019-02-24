package kyu7

import java.time.{DayOfWeek, LocalDate}

object UnluckyDays {
  def unluckyDays(year: Int): Int =
    (1 to 12)
      .map(LocalDate.of(year, _, 13))
      .count(_.getDayOfWeek == DayOfWeek.FRIDAY)
}

// Concise
//Month.values.count(LocalDate.of(year, _, 13).getDayOfWeek == DayOfWeek.FRIDAY)

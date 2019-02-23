package kyu6

//http://cquiroz.github.io/scala-java-time/
import java.time.{DayOfWeek, LocalDate}

object MostFrequentWeekdays {
  def mostFrequentDays(year: Int): List[String] = {
    val counter = daysCounter(
      Map.empty[DayOfWeek, Int],
      LocalDate.of(year + 1, 1, 1),
      LocalDate.of(year, 1, 1)
    )

    val mostFrequentWeekdays: List[DayOfWeek] = counter.filter(_._2 == counter.values.max).keys.toList
    DayOfWeek.values.toList.intersect(mostFrequentWeekdays).map(_.toString.toLowerCase.capitalize)
  }

  @scala.annotation.tailrec
  private def daysCounter(counter: Map[DayOfWeek, Int], endOfYear: LocalDate, day: LocalDate): Map[DayOfWeek, Int] =
    day match {
      case `endOfYear` => counter
      case _           =>
        val dayOfWeek = day.getDayOfWeek
        val newCount = counter.getOrElse(dayOfWeek, 0) + 1

        val newCounter = counter + (dayOfWeek -> newCount)
        daysCounter(newCounter, endOfYear, day.plusDays(1))
    }
}

// Streams usage
//object Kata {
//  def mostFrequentDays(year: Int): List[String] = {
//    Stream
//      .iterate(LocalDate.ofYearDay(year, 1))(_.plusDays(1))
//      .takeWhile(_.getYear == year)
//      .map(_.getDayOfWeek)
//      .groupBy(identity)
//      .mapValues(_.size)
//      .filter(_._2 == 53)
//      .keys
//      .toList
//      .sorted
//      .map(_.toString.toLowerCase.capitalize)
//  }
//}

// Efficient way of doing it, without iterating through all the days
//object Kata {
//  val weekDays = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
//  def mostFrequentDays(year: Int): List[String] = {
//    val firstDay = LocalDate.of(year, 1, 1)
//    val dayNum = firstDay.getDayOfWeek.ordinal
//
//    (firstDay.isLeapYear, dayNum) match {
//      case (true, 6) => List(weekDays(0), weekDays(6))
//      case (true, _) => List(weekDays(dayNum), weekDays(dayNum + 1))
//      case _ => List(weekDays(dayNum))
//    }
//  }
//}

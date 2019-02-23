package kyu6

import org.scalatest.FlatSpec

class MostFrequentWeekdaysSpec extends FlatSpec {
  it should "work with example tests" in {
    assert(MostFrequentWeekdays.mostFrequentDays(2427) == List("Friday"))
    assert(MostFrequentWeekdays.mostFrequentDays(2185) == List("Saturday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1084) == List("Tuesday", "Wednesday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1167) == List("Sunday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1216) == List("Friday", "Saturday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1492) == List("Friday", "Saturday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1770) == List("Monday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1785) == List("Saturday"))
    assert(MostFrequentWeekdays.mostFrequentDays(212) == List("Wednesday", "Thursday"))
    assert(MostFrequentWeekdays.mostFrequentDays(1) == List("Monday"))
  }
}

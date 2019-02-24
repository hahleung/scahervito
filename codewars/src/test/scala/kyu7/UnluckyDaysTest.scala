package kyu7

import org.scalatest.FlatSpec

class UnluckyDaysTest extends FlatSpec {
  it should "work with example tests" in {
    assert(UnluckyDays.unluckyDays(2015) == 3)
    assert(UnluckyDays.unluckyDays(1986) == 1)
  }
}

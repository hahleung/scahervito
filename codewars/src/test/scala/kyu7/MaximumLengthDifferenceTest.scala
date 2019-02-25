package kyu7

import org.scalatest._

// (cd codewars; sbt "test:testOnly *kyu7.MaximumLengthDifferenceTest")
class MaximumLengthDifferenceTest extends FlatSpec with Matchers {
  val a1 = List("hoqq", "bbllkw", "oox", "ejjuyyy", "plmiis", "xxxzgpsssa", "xxwwkktt", "znnnnfqknaz", "qqquuhii", "dvvvwz")
  val a2 = List("cccooommaaqqoxii", "gggqaffhhh", "tttoowwwmmww")
  val result = 13

  s"mxdiflg($a1, $a2)" should "return 13" in {
    MaximumLengthDifference.mxdiflg(a1, a2) should be(13)
  }

  "First array is empty" should "return -1" in {
    MaximumLengthDifference.mxdiflg(List(), List("lol")) should be(-1)
  }

  "Second array is empty" should "return -1" in {
    MaximumLengthDifference.mxdiflg(List("lol"), List()) should be(-1)
  }
}

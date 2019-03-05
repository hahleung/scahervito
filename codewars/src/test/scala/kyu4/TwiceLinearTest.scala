package kyu4

import org.scalatest._
import org.scalatest.Assertions._
import TwiceLinearTest._

class TwiceLinearTest extends FlatSpec {
  it should "pass basic tests" in {
    testing(50, 175)
//    testing(10, 22)
//    testing(20, 57)
//    testing(30, 91)
  }
}

object TwiceLinearTest {
  private def testing(n: Int, expect: Int): Unit = {
    println("Testing: " + n)
    val actual: Int = TwiceLinear.dblLinear(n)
    println("Actual: " + actual)
    println("Expect: " + expect)
    assertResult(expect){actual}
  }
}

import org.scalatest._
import org.scalatest.Assertions._

import QuadraticTest._

//(cd codewars; sbt "test:testOnly *QuadraticTest")
class QuadraticTest extends FlatSpec {
  it should "pass basic tests" in {
    assertFuzzyEquals(7, 4.00e+13, 8)
    assertFuzzyEquals(9, 1.00e+14, 1)
    assertFuzzyEquals(3, 3.00e+09, 1)
  }
}

object QuadraticTest {
  private def assertFuzzyEquals(a: Double, b: Double, c: Double): Unit = {
    val merr: Double = 1e-12
    println("Testing " + a + ", " + b + ", " + c)
    val x: Double = Quadratic.quadratic(a, b, c)
    val smallest: Boolean = Math.abs(x) < 0.1
    if (smallest == false) {
      println("This root is not the good one")
    }
    val actual: Double = a * x * x + b * x + c
    val inrange: Boolean = Math.abs(actual) <= merr
    if (inrange == false)
      println("Expected must be near " + 0.0 + ", got " + actual)
    val correct: Boolean = smallest && inrange
    assertResult(true) {
      correct
    }
  }
}

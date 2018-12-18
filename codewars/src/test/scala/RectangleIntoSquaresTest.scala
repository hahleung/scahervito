import org.scalatest._
import org.scalatest.Assertions._
import RectangleIntoSquaresTest._

//(cd codewars; sbt "test:testOnly *RectangleIntoSquaresTest")
class RectangleIntoSquaresTest extends FlatSpec {
  it should "pass basic tests" in {
    testing(5, 3, Array(3, 2, 1, 1))
    testing(3, 5, Array(3, 2, 1, 1))
    testing(5, 5, Array())
  }
}

object RectangleIntoSquaresTest {
  private def testing(min: Int, max: Int, expect: Array[Int]): Unit = {
    println("MIN: " + min + " MAX: " + max)
    val actual: Array[Int] = RectangleIntoSquares.sqInRect(min, max)
    println("Actual: " + actual.mkString(", "))
    println("Expect: " + expect.mkString(", "))
    println("-")
    assertResult(expect) {
      actual
    }
  }
}

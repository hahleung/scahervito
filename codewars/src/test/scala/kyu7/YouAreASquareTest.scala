package kyu7

// You can test using ScalaTest (http://www.scalatest.org/).
import org.scalatest._

//(cd codewars; sbt "test:testOnly *kyu7.YouAreASquareTest")
class YouAreASquareTest extends FlatSpec with Matchers {
  val tests = List(
    (-1, false),
    (0, true),
    (3, false),
    (4, true),
    (25, true),
    (26, false),
    (154, false),
    (88, false),
    (118, false)
  )

  tests.foreach {
    case (input, expected) =>
      s"isSquare($input)" should s"return $expected" in {
        YouAreASquare.isSquare(input) should be(expected)
      }
  }
}

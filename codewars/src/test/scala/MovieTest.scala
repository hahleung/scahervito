import org.scalatest._
import org.scalatest.Assertions._

import MovieTest._

// (cd codewars; sbt "test:testOnly *MovieTest")
class MovieTest extends FlatSpec {
  it should "pass basic tests" in {
    testing(500, 15, 0.9, 43)
    testing(100, 10, 0.95, 24)
    testing(0, 10, 0.95, 2)
    testing(114, 50, 0.44, 4)
  }
}

object MovieTest {
  private def testing(card: Int, ticket: Int, perc: Double, expect: Int): Unit = {
    println("Testing: " + card + ", " + ticket + ", " + perc)
    val actual: Int = Movie.movie(card, ticket, perc)
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("*")
    assertResult(expect) {
      actual
    }
  }
}

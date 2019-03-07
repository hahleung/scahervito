package kyu3

import org.scalatest._

class PsychicTest extends FlatSpec with Matchers {
  "The Psychic" should "guess correctly" in {
    Psychic.guess() shouldBe java.lang.Math.random()
  }
}

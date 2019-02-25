package kyu7

import org.scalatest._

// (cd codewars; sbt "test:testOnly *ColoredTriangleTest")
class ColoredTriangleTest extends FlatSpec with Matchers {
  "triangle(\"GB\")" should "return R" in {
    ColoredTriangle.triangle("GB") should be("R")
  }
  "triangle(\"RRR\")" should "return R" in {
    ColoredTriangle.triangle("RRR") should be("R")
  }
  "triangle(\"RGBG\")" should "return B" in {
    ColoredTriangle.triangle("RGBG") should be("B")
  }
  "triangle(\"RBRGBRB\")" should "return G" in {
    ColoredTriangle.triangle("RBRGBRB") should be("G")
  }
  "triangle(\"RBRGBRBGGRRRBGBBBGG\")" should "return G" in {
    ColoredTriangle.triangle("RBRGBRBGGRRRBGBBBGG") should be("G")
  }
  "triangle(\"B\")" should "return B" in {
    ColoredTriangle.triangle("B") should be("B")
  }
}

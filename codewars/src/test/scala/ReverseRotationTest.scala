import org.scalatest._
import org.scalatest.Assertions._

import ReverseRotationTest._

//(cd codewars; sbt "test:testOnly *ReverseRotationTest")
class ReverseRotationTest extends FlatSpec {
  it should "pass basic tests" in {
    testing("1234", 0, "")
    testing("", 0, "")
    testing("1234", 5, "")
    var s: String = "733049910872815764"
    testing(s, 5, "330479108928157")
    s = "73304991087281576455176044327690580265896"
    testing(s, 8, "1994033775182780067155464327690480265895")

  }
}

object ReverseRotationTest {
  private def testing(s: String, sz: Int, expect: String): Unit = {
    val actual = ReverseRotation.revRot(s, sz)
    println("Testing: " + s + ", " + sz)
    println("Actual --> " + actual)
    println("Expect --> " + expect)
    println("-")
    assertResult(expect) {
      actual
    }
  }
}

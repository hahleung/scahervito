package kyu6

import org.scalatest._
import org.scalatest.Assertions._

import OpstringTerTest._

class OpstringTerTest extends FlatSpec {
  it should "pass basic tests operArray rot" in {
    testing(OpstringTer.oper(OpstringTer.rot, "fijuoo\nCqYVct\nDrPmMJ\nerfpBA\nkWjFUG\nCVUfyL"),
      "LyfUVC\nGUFjWk\nABpfre\nJMmPrD\ntcVYqC\nooujif")
  }

  it should "pass basic tests operArray selfieAndRot" in {
    testing(OpstringTer.oper(OpstringTer.selfieAndRot, "xZBV\njsbS\nJcpN\nfVnP"),
      "xZBV....\njsbS....\nJcpN....\nfVnP....\n....PnVf\n....NpcJ\n....Sbsj\n....VBZx")
  }
}

object OpstringTerTest {
  private def testing(actual: String, expect: String): Unit = {
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("-")
    assertResult(expect){actual}
  }
}

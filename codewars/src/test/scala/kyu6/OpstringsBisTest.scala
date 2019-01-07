package kyu6

import org.scalatest._
import org.scalatest.Assertions._

import OpstringsBisTest._

//(cd codewars; sbt "test:testOnly *OpstringsBisTest")
class OpstringsBisTest extends FlatSpec {
  it should "pass basic tests operArray rot90Counter" in {
    testing(OpstringsBis.oper(OpstringsBis.rot90Counter, "EcGcXJ\naaygcA\nNgIshN\nyOrCZE\neBEqpm\nNkxCgw"),
      "JANEmw\nXchZpg\ncgsCqC\nGyIrEx\ncagOBk\nEaNyeN")

  }
  it should "pass basic tests operArray diag2Sym" in {
    testing(OpstringsBis.oper(OpstringsBis.diag2Sym, "LmvLyg\nDKELBm\nylJhui\nXRXqHD\nzlisCT\nhPqxYb"),
      "bTDimg\nYCHuBy\nxsqhLL\nqiXJEv\nPlRlKm\nhzXyDL")

  }
  it should "pass basic tests operArray selfieDiag2Counterclock" in {
    testing(OpstringsBis.oper(OpstringsBis.selfieDiag2Counterclock, "NJVGhr\nMObsvw\ntPhCtl\nsoEnhi\nrtQRLK\nzjliWg"),
      "NJVGhr|gKilwr|rwliKg\nMObsvw|WLhtvh|hvthLW\ntPhCtl|iRnCsG|GsCnRi\nsoEnhi|lQEhbV|VbhEQl\nrtQRLK|jtoPOJ|JOPotj\nzjliWg|zrstMN|NMtsrz");

  }
}

object OpstringsBisTest {
  private def testing(actual: String, expect: String): Unit = {
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("-")
    assertResult(expect){actual}
  }
}



package kyu5

import org.scalatest._
import org.scalatest.Assertions._

import CodingWithSquaredStringsTest._

//(cd codewars; sbt "test:testOnly *CodingWithSquaredStringsTest")
class CodingWithSquaredStringsTest extends FlatSpec {
  val data1 = "What do you remember? When I looked at his streaky glasses, I wanted " +
    "to leave him. And before that? He stole those cherries for me at midnight. We were walking " +
    "in the rain and I loved him. And before that? I saw him coming " +
    "toward me that time at the picnic, edgy, foreign."

  val data1Sol =
    "\u000bctg?.nadr d gdbW\n\u000b,i    lnis tl eh\n\u000b mtIAakietboaara\n\u000beeo nnigsoe st?t\n\u000bd wsddnh lfls   \n\u000bgaaa  gtfeoeehWd\n" +
      "\u000bytrwbI .o rasiho\n\u000b, d e i rtev,se \n\u000b t hflnW h e  ny\n\u000bfhmioo emot Is o\n\u000boeemrvt eshh tIu\n\u000br   eehw eaiwr  \n" +
      "\u000beptc deea tmaelr\n\u000biihot  rtc?.naoe\n\u000bgcamhhre h  tkom\n\u000bnntiaia meHAeyke\n\u000b.i ntmiwirend em"


  it should "pass basic tests code" in {
    testing(CodingWithSquaredStrings.code(data1), data1Sol)
  }

  it should "pass basic tests decode" in {
    testing(CodingWithSquaredStrings.decode(data1Sol), data1)
  }

  it should "edge case - code empty string" in {
    testing(CodingWithSquaredStrings.code(""), "")
  }

  it should "edge case - decode empty string" in {
    testing(CodingWithSquaredStrings.decode(""), "")
  }

}

object CodingWithSquaredStringsTest {
  private def testing(actual: String, expect: String): Unit = {
    println("Actual: " + actual)
    println("\nExpect: " + expect)
    println("-")
    assertResult(expect) {
      actual
    }
  }
}



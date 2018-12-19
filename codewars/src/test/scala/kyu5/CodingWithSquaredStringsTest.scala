package kyu5

//A squared string has n lines, each substring being n characters long: For example:
//
//s = "abcd\nefgh\nijkl\nmnop" is a squared string of size 4.
//
//We will use squared strings to code and decode texts. To make things easier we suppose that our original text doesn't include the character '\n'.
//
//#Coding Input:
//
//a text t of length l.
//Consider the smallest integer n such that n * n be greater or equal to l.
//Complete t with the char of ascii code 11 (we suppose that this char is not in our original text) until the length of t is n * n.
//From now on we can transform the new t in a squared string s of size n by inserting '\n' where needed.
//Apply a clockwise rotation of 90 degrees to s: that's it for the coding part.
//#Decoding Input:
//
//a squared string s resulting from the coding
//Apply a counter-clockwise rotation of 90 degrees to s
//Do some cleaning to have the original text t
//You can see clockwise rotation of 90 degrees: http://www.codewars.com/kata/56dbeec613c2f63be4000be6 You can see counter-clockwise rotation of 90 degrees: http://www.codewars.com/kata/56dbf59b0a10feb08c000227
//
//Example:
//
//t = "I.was.going.fishing.that.morning.at.ten.o'clock"
//
//code(t) -> "c.nhsoI\nltiahi.\noentinw\ncng.nga\nk..mg.s\n\voao.f.\n\v'trtig"
//
//decode(code(t)) == "I.was.going.fishing.that.morning.at.ten.o'clock"
//
//(Dots indicate spaces since they are quite invisible).
//
//Notes:
//Swift : character 11 is replaced by "\u{F7}" (ie "÷" - alt 246 -)
//Ocaml : character 11 is replaced by '&'
//Fortran: Your returned string for both functions are not permitted to contain redundant leading/trailing whitespace. In return, you may safely assume that all input strings passed into your function(s) will not contain redundant leading/trailing whitespace, i.e. you do not and should not trim the input string before operating on it
//Don't use this coding to keep your secrets:-)

import org.scalatest._
import org.scalatest.Assertions._

import CodingWithSquaredStringsTest._

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
    testing(HelpYourGranny.code(data1), data1Sol)

  }
  it should "pass basic tests decode" in {
    testing(HelpYourGranny.decode(data1Sol), data1)

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



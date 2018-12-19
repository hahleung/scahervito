//Coding decimal numbers with factorials is a way of writing out numbers in a base system that depends on factorials, rather than powers of numbers.
//  In this system, the last digit is always 0 and is in base 0!.
//The digit before that is either 0 or 1 and is in base 1!. The digit before that is either 0, 1, or 2 and is in base 2!. More generally, the nth-to-last digit in always 0, 1, 2, ..., or n and is in base n!.
//
//Example : decimal number 463 is coded as "341010"
//
//because 463 (base 10) = 3×5! + 4×4! + 1×3! + 0×2! + 1×1! + 0×0!
//
//If we are limited to digits 0...9 the biggest number we can code is 10! - 1.
//
//So we extend 0..9 with letters A to Z. With these 36 digits we can code up to 36! − 1 = 37199332678990121746799944815083519999999910 (base 10)
//
//We code two functions, the first one will code a decimal number and return a string with the factorial representation : "dec2FactString(nb)"
//
//the second one will decode a string with a factorial representation and produce the decimal representation : "factString2Dec(str)".
//
//Given numbers will be positive.
//
//  Note
//You have tests with Big Integers in Clojure, Python, Ruby, Haskell, Ocaml but not with Java and others where the number "nb" in "dec2FactString(nb)" is at most a long.
//
//Ref: http://en.wikipedia.org/wiki/Factorial_number_system

package kyu5

import org.scalatest._
import org.scalatest.Assertions._

import DecimalToFactorialAndBackTest._

class DecimalToFactorialAndBackTest extends FlatSpec {
  it should "pass basic tests dec2FactString" in {
    testing1(36288000L, "A0000000000")
    testing1(2982L, "4041000")

  }
  it should "pass basic tests factString2Dec" in {
    testing2("341010", 463L)
    testing2("4042100", 2990L)

  }
}

object DecimalToFactorialAndBackTest {
  private def testing1(nb: Long, expect: String): Unit = {
    println("Testing1: " + nb)
    val actual: String = DecimalToFactorialAndBack.dec2FactString(nb)
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("-")
    assertResult(expect) {
      actual
    }
  }

  private def testing2(str: String, expect: Long): Unit = {
    println("Testing2: " + str)
    val actual: Long = DecimalToFactorialAndBack.factString2Dec(str)
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("-")
    assertResult(expect) {
      actual
    }
  }
}



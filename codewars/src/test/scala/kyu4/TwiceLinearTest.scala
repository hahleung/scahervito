package kyu4

import org.scalatest._
import org.scalatest.Assertions._
import TwiceLinearTest._

class TwiceLinearTest extends FlatSpec {
  it should "pass basic tests" in {
    testing(50, 175)
    testing(10, 22)
    testing(20, 57)
    testing(30, 91)
    testing(6000, 80914)
  }
}

object TwiceLinearTest {
  private def testing(n: Int, expect: Int): Unit = {
    val t1 = System.currentTimeMillis
    println("\nTesting: " + n)
    val actual: Int = TwiceLinear.dblLinear(n)
    val t2 = System.currentTimeMillis
    println((t2 - t1) + " msecs")
    println("Actual: " + actual)
    println("Expect: " + expect)
    assertResult(expect){actual}
  }
}

//Benchmark
//val newList = t ++ List(h * 2 + 1, h * 3 + 1)
//doIteration(newList.distinct.sorted, index - 1)
//Testing: 6000
//2011 msecs
//Actual: 80914
//Expect: 80914

//val newList = (2 * h + 1) :: (3 * h + 1) :: t
//doIteration(newList.distinct.sorted, index - 1)
//Testing: 6000
//1755 msecs
//Actual: 80914
//Expect: 80914

//val newSortedSet = sortedSet.tail + (2 * sortedSet.head + 1) + (3 * sortedSet.head + 1)
//doIteration(newSortedSet, index - 1)
//Testing: 6000
//113 msecs
//Actual: 80914
//Expect: 80914

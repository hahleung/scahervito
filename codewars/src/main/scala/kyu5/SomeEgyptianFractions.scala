package kyu5

object SomeEgyptianFractions {

  def decompose(nrStr: String, drStr: String): String = {
    val (numerator, denominator) = parseInputs(nrStr.toInt, drStr.toInt)

    println(numerator)
    val egyptianFractions = numerator match {
      case 1 => List(denominator)
      case _ => getFractions(numerator, denominator)
    }

    addBrackets(formatDenominators(egyptianFractions))
  }

  private def parseInputs(numerator: Int, denominator: Int): (Int, Int) =
    denominator % numerator match {
      case 0 => (numerator / numerator, denominator / numerator)
      case _ => (numerator, denominator)
    }

  private def getFractions(numerator: Int, denominator: Int) = numerator % denominator match {
    case 0 => divide(numerator / denominator)
    case _ => getEgyptianFractions(numerator, denominator)
  }


  private def divide(divisionResult: Int): List[Int] =
    if (divisionResult == 0) List() else List(divisionResult)

  private def getEgyptianDenominator(numerator: Int, denominator: Int): Int = {
    val division = denominator / numerator
    if (denominator % numerator == 0) division else division + 1
  }

  private def getNextFraction(numerator: Int, denominator: Int, egyptianDenominator: Int): (Int, Int) = {
    val nextNumerator = numerator * egyptianDenominator - denominator
    val nextDenominator = denominator * egyptianDenominator
    if (nextDenominator % nextNumerator == 0) (1, nextDenominator / nextNumerator) else (nextNumerator, nextDenominator)
  }

  private def getEgyptianFractions(numerator: Int, denominator: Int): List[Int] =
    doGetEgyptianFractions(numerator, denominator, List())

  @scala.annotation.tailrec
  private def doGetEgyptianFractions(numerator: Int, denominator: Int, egyptianDenominators: List[Int]): List[Int] =
    numerator match {
      case 1 => denominator :: egyptianDenominators
      case _ =>
        val egyptianDenominator = getEgyptianDenominator(numerator, denominator)
        val (nextNumerator, nextDenominator) = getNextFraction(numerator, denominator, egyptianDenominator)
        doGetEgyptianFractions(nextNumerator, nextDenominator, egyptianDenominator :: egyptianDenominators)
    }

  private def toFraction(denominator: Int): String = denominator match {
    case 1 => "1"
    case _ => "1/" + denominator
  }

  private def formatDenominators(egyptianFractions: List[Int]): String =
    egyptianFractions match {
      //        Issue with Singleton: 20/5 is [4] but 5/20 is [1/4]
      case List(singleton) => singleton.toString
      case _ => egyptianFractions.reverse.map(toFraction).mkString(", ")
    }

  private def addBrackets(denominators: String): String = "[" + denominators + "]"
}

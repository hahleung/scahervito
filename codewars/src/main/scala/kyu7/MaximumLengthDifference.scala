package kyu7

object MaximumLengthDifference {
  // First iteration was made of these functions:
  // def getSortedList(list: List[String]): List[String] = list.sortBy(a => a.size)
  // def getMinSize(list: List[String]): Int = list.headOption.getOrElse("").size
  // def getMaxSize(list: List[String]): Int = getMinSize(list.reverse)

  // Apparently "mxdiflg" is an excellent name for the exercise conceptor
  def mxdiflg(a1: List[String], a2: List[String]): Int = {
    (a1, a2) match {
      case (List(), _) => -1
      case (_, List()) => -1
      case _ => findMaxLengthDifference(a1, a2)
    }
  }

  private def findMaxLengthDifference(a1: List[String], a2: List[String]): Int = {
    val (minA1, maxA1) = getMinAndMax(a1)
    val (minA2, maxA2) = getMinAndMax(a2)

    (maxA1 - minA2) max (maxA2 - minA1)
  }

  private def getMinAndMax(list: List[String]): (Int, Int) = {
    val firstElement = list.head.length
    val seed = (firstElement, firstElement)
    val foldingFunction = (minMax: (Int, Int), element: String) =>
      (element.length min minMax._1, element.length max minMax._2)

    list.foldLeft(seed)(foldingFunction)
  }
}

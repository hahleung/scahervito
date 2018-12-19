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

//This one looks concise and elegant, but performances are really doubtful
//It's generating all the differences and take the max, unsuitable for huge a1, a2
//object Kata {
//  def mxdiflg(a1: List[String], a2: List[String]): Int =
//    (for { x <- a1; y <- a2 } yield (y.size - x.size).abs) match { case Nil => -1 case l => l.max }
//}

//Same comment
//object Kata {
//  def mxdiflg(a1: List[String], a2: List[String]): Int = {
//    if (a1.isEmpty || a2.isEmpty) return -1
//
//    val List(l1, l2) = List(a1, a2).map(_.map(_.length))
//    List(l1.max - l2.min, l1.min - l2.max).map(Math.abs(_)).max
//  }
//}

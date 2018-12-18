object ColoredTriangle {
  def triangle(row: String): String = {
    def reduceColors(colorsList: List[Char]): List[Char] = {
      colorsList match {
        case colorsList if (colorsList.size == 1) => colorsList
        case _ =>
          val newColorsList = colorsList.zip(colorsList.tail).map(getComplementaryColor)
          reduceColors(newColorsList)
      }
    }

    def getComplementaryColor(colorsPair: (Char, Char)): Char = {
      val sortedColorsPair = colorsPair match {
        case (higherColor, lowerColor) if (higherColor > lowerColor) => (lowerColor, higherColor)
        case _ => colorsPair
      }

      sortedColorsPair match {
        case ('B', 'G') => 'R'
        case ('B', 'R') => 'G'
        case ('G', 'R') => 'B'
        case (sameColor, _) => sameColor
      }
    }

    reduceColors(row.toList).head.toString
  }
}

//object Sol {
//  val colours = Set('R','G','B')
//  def triangle(row: String): String =
//    if(row.length == 1) row
//    else triangle(row.zip(row.tail).map {
//      case(c, d) if c == d => c
//      case(c, d) => (colours - c - d).head }.mkString)
//}
//
//object Sol {
//  def triangle(row: String): String =
//    if (row.length == 1) row
//    else triangle(row.sliding(2).map(colors).mkString)
//
//  def colors(in: String): Char =
//    if (in(0) == in(1)) in(0)
//    else "RGB".diff(in).head
//}
//
//object Sol {
//  def triangle(row: String): String =
//    if (row.size == 1)
//      row
//    else
//      triangle(row.sliding(2).map { case s if s(0) == s(1) => s(0) case s => "RGB".diff(s).head }.mkString)
//}

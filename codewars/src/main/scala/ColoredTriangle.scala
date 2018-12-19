object ColoredTriangle {
  def triangle(row: String): String = {
    val colorsSet = Set('R', 'G', 'B')

    @annotation.tailrec
    def reduceColors(colorsList: List[Char]): List[Char] =
      if (colorsList.size == 1) colorsList
      else reduceColors(getNewList(colorsList))

    def getNewList(colorsList: List[Char]): List[Char] =
      colorsList.zip(colorsList.tail).map(getComplementaryColor)

    def getComplementaryColor(colorsPair: (Char, Char)): Char =
      colorsPair match {
        case (firstColor, secondColor) if firstColor != secondColor =>
          (colorsSet - firstColor - secondColor).head
        case (sameColor, _) => sameColor
      }

    reduceColors(row.toList).head.toString
  }
}

//My original response
//object ColoredTriangle {
//  def triangle(row: String): String = {
//    def reduceColors(colorsList: List[Char]): List[Char] = {
//      colorsList match {
//        case colorsList if colorsList.size == 1 => colorsList
//        case _ =>
//          val newColorsList = colorsList.zip(colorsList.tail).map(getComplementaryColor)
//          reduceColors(newColorsList)
//      }
//    }
//
//    def getComplementaryColor(colorsPair: (Char, Char)): Char = {
//      val sortedColorsPair = colorsPair match {
//        case (higherColor, lowerColor) if higherColor > lowerColor => (lowerColor, higherColor)
//        case _ => colorsPair
//      }
//
//      sortedColorsPair match {
//        case ('B', 'G') => 'R'
//        case ('B', 'R') => 'G'
//        case ('G', 'R') => 'B'
//        case (sameColor, _) => sameColor
//      }
//    }
//
//    reduceColors(row.toList).head.toString
//  }
//}

//Interesting usage of Set
//object Sol {
//  val colours = Set('R','G','B')
//  def triangle(row: String): String =
//    if(row.length == 1) row
//    else triangle(row.zip(row.tail).map {
//      case(c, d) if c == d => c
//      case(c, d) => (colours - c - d).head }.mkString)
//}

//Very elegant with `sliding` and `diff` - Recursion directly on string
//to recurse directly on the primary function
//object Sol {
//  def triangle(row: String): String =
//    if (row.length == 1) row
//    else triangle(row.sliding(2).map(colors).mkString)
//
//  def colors(in: String): Char =
//    if (in(0) == in(1)) in(0)
//    else "RGB".diff(in).head
//}

//object Sol {
//  def triangle(row: String): String =
//    if (row.size == 1)
//      row
//    else
//      triangle(row.sliding(2).map { case s if s(0) == s(1) => s(0) case s => "RGB".diff(s).head }.mkString)
//}

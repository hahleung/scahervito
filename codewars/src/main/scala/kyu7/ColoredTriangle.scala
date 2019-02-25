package kyu7

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

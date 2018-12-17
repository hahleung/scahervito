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

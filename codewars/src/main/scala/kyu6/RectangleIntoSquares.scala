package kyu6

object RectangleIntoSquares {
  def sqInRect(min: Int, max: Int): Array[Int] = {
    if (min == max) Array()
    else go(min min max, min max max, Array())
  }

  @annotation.tailrec
  private def go(min: Int, max: Int, squares: Array[Int]): Array[Int] = {
    val newSquares = squares ++ Array(min)

    if (min == max) newSquares
    else {
      val newDimension = max - min
      val newMax = newDimension max min
      val newMin = newDimension min min
      go(newMin, newMax, newSquares)
    }
  }
}

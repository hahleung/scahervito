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

// This solution is changing the signature
//object SqInRect {
//  def sqInRect(min: Int, max: Int, squares: Array[Int] = Array()): Array[Int] = {
//    val Array(width, length) = Array(min, max).sorted
//    if (width == length)
//      if (squares.isEmpty) Array() else (width +: squares).reverse
//    else
//      sqInRect(length - width, width, width +: squares)
//  }
//}

// This is not really clean, but it's concise
//object SqInRect {
//  def sqInRect(min: Int, max: Int): Array[Int] = {
//    def sqInRect_(min: Int, max: Int): Array[Int] =
//      if (min == 0) Array() else Array.fill(max / min)(min) ++ sqInRect_(max % min, min)
//
//    if (min == max) Array() else sqInRect_(if (min < max) min else max, if (min > max) min else max)
//  }
//}

object Quadratic {
  def quadratic(a: Double, b: Double, c: Double): Double = {
    val firstPart = -b / (2 * a)
    val secondPart = math.sqrt(math.pow(b, 2) - 4 * a * c) / (2 * a)

    val firstRoot = firstPart - secondPart
    val secondRoot = firstPart + secondPart

    println(firstRoot)
    println(secondRoot)

    math.abs(firstPart) > math.abs(secondPart) match {
      case true => secondRoot
      case false => firstRoot
    }
  }
}

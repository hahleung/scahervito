object YouAreASquare {
  def isSquare(potentialSquare: Int): Boolean = {
    val potentialRoot = math.sqrt(potentialSquare).toInt
    potentialSquare.toDouble == math.pow(potentialRoot, 2)
  }
}

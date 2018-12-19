object YouAreASquare {
  def isSquare(potentialSquare: Int): Boolean = {
    val potentialRoot = math.sqrt(potentialSquare).toInt
    potentialSquare.toDouble == math.pow(potentialRoot, 2)
  }
}

// math.sqrt(x).isWhole
// math.sqrt(x).isValidInt
// return Math.round(Math.sqrt(x))*Math.round(Math.sqrt(x))== x
// List(0, 1) contains Math.sqrt(x)%2

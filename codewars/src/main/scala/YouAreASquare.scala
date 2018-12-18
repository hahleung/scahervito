object YouAreASquare {
  def isSquare(potentialSquare: Int): Boolean = {
    val potentialRoot = math.sqrt(potentialSquare)
    potentialSquare.toDouble == math.pow(potentialRoot, 2)
  }
}

//isSquare(154) should return false
//88 118
//Test Failed
//  true was not false
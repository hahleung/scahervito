object ExesAndOhs {
  def xo(string: String): Boolean = {
    val lettersMap = string.toList.groupBy(identity).mapValues(_.size)
    getInsensitiveLetterCount(lettersMap, 'x') == getInsensitiveLetterCount(lettersMap, 'o')
  }

  private def getInsensitiveLetterCount(lettersMap: Map[Char, Int], letter: Char): Int =
    lettersMap.getOrElse(letter, 0) + lettersMap.getOrElse(letter.toUpper, 0)
}

//object ExesAndOhs {
//  def xo(str: String): Boolean =
//    str.count(_.toLower == 'x') == str.count(_.toLower == 'o')
//}

//object ExesAndOhs {
//  def xo(str: String): Boolean =
//    str.count(c => c == 'X' || c == 'x') == str.count(c => c == 'O' || c == 'o')
//}

//object ExesAndOhs {
//  def xo(str: String): Boolean = {
//    val xO = str.toLowerCase().groupBy(identity).mapValues(_.size)
//    if ((xO.contains('x')) && (xO.contains('o')) && (xO('x') != xO('o'))) false
//    else true
//  }
//}

package kyu7

object ExesAndOhs {
  def xo(string: String): Boolean = {
    val lettersMap = string.toList.groupBy(identity).mapValues(_.size)
    getInsensitiveLetterCount(lettersMap, 'x') == getInsensitiveLetterCount(lettersMap, 'o')
  }

  private def getInsensitiveLetterCount(lettersMap: Map[Char, Int], letter: Char): Int =
    lettersMap.getOrElse(letter, 0) + lettersMap.getOrElse(letter.toUpper, 0)
}

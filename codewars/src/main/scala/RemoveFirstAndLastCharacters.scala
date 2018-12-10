object RemoveFirstAndLastCharacters {
  def removeChars(string: String): String = {
    string.substring(1, string.length - 1)

    // string.drop(1).dropRight(1)
    // string.tail.init
  }
}

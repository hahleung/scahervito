object TwiceAsOld {
  def twiceAsOld(dad: Int, son: Int): Int = {
    val difference = (dad - 2 * son)
    if (difference < 0) -difference else difference

    // Most concise
    // (dad - 2 * son).abs
  }
}

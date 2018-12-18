object Mumbling {
  def accum(unmumbled: String): String = {
    @annotation.tailrec
    def go(unmumbled: String, mumbled: List[String]): List[String] =
      unmumbled.headOption match {
        case Some(head) =>
          val newMumbled = getMumbled(mumbled, head)
          go(unmumbled.tail, newMumbled)
        case None => mumbled.init.reverse
      }

    go(unmumbled, List("")).mkString("-")
  }

  private def getMumbled(mumbled: List[String], currentLetter: Char): List[String] = {
    val frequency = mumbled.head.length
    val mumbledItem = currentLetter.toUpper :: List.fill(frequency)(currentLetter.toLower)
    mumbledItem.mkString :: mumbled
  }
}

//    s.indices.map(i => s(i).toLower.toString * (i+1) capitalize).mkString("-")
//    s.zipWithIndex.map{ case (c, i) => c.toUpper + c.toLower.toString * i }.mkString("-")

package kyu6

object ReverseRotation {
  def revRot(sequence: String, chunkSize: Int): String = {
    if (sequence.length < chunkSize) ""
    else if (chunkSize <= 0) ""
    else {
      sequence
        .toList
        .grouped(chunkSize)
        .filter(_.size == chunkSize)
        .map(reverseOrRotate)
        .map(_.mkString)
        .mkString
    }
  }

  private def reverseOrRotate(list: List[Char]): List[Int] = {
    val ints = list.map(_.toString.toInt)
    if (IsSumCubeIntsDivisibleBy2(ints)) ints.reverse
    else rotateIt(ints)
  }

  // I guess sometimes Code Challenge inventors are running out of idea
  private def IsSumCubeIntsDivisibleBy2(list: List[Int]): Boolean =
    list.map(math.pow(_, 3)).sum % 2 == 0

  private def rotateIt(ints: List[Int]): List[Int] = {
    val head :: tail = ints
    tail ++ List(head)
  }
}

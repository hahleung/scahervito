package kyu4

object TwiceLinear {
  def dblLinear(n: Int): Int = doIteration(List(1), n)

  @scala.annotation.tailrec
  private def doIteration(list: List[Int], index: Int): Int =
    index match {
      case 0 => list.head
      case _ =>
        val h :: t = list
        val newList = t ++ List(h * 2 + 1, h * 3 + 1)
        doIteration(newList.distinct.sorted, index - 1)
    }
}

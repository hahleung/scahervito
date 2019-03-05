package kyu4

object TwiceLinear {
  def dblLinear(n: Int): Int = {
    val x = doIteration(List.empty, List(1), n)
    x(n)
  }

  @scala.annotation.tailrec
  private def doIteration(list: List[Int], nextIterationList: List[Int], index: Int): List[Int] = {
    val updatedList = (list ++ nextIterationList).distinct
    updatedList.size < index + 1 match {
      case false => updatedList.sorted
      case true  =>
        val updatedNextIterationList = rampIteration(list, nextIterationList, List.empty, index)
//            println(s"main ${list.size}; updatedNextIterationList ${updatedNextIterationList.size}")
        doIteration(updatedList, updatedNextIterationList, index)
    }
  }

  private def rampIteration(mainList: List[Int], candidates: List[Int], iteratedCandidates: List[Int], index: Int): List[Int] = {
//    println(s"main ${mainList.size}; candidates ${candidates.size}; iterated ${iteratedCandidates.size}; sum? ${(mainList ++ iteratedCandidates).size}")
    (mainList ++ iteratedCandidates).size < index match {
      case false => iteratedCandidates
      case true  =>
        candidates.size match {
          case 0 => iteratedCandidates
          case _ =>
            val head :: tail = candidates
            val updatedIteratedCandidates = iteratedCandidates ++ List(firstLinearResult(head), secondLinearResult(head)).filterNot(x => mainList.contains(x) || iteratedCandidates.contains(x))
            rampIteration(mainList, tail, updatedIteratedCandidates, index)
        }
    }
  }

  private def firstLinearResult(x: Int) = 2 * x + 1

  private def secondLinearResult(x: Int) = 3 * x + 1

}

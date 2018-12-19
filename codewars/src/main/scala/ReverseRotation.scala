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

//`- 48` is super clumsy
//It's pretty concise, as strings are the only transformed primitive
//object RevRot {
//  def revRot(strng: String, sz: Int): String = {
//    if (sz <= 0 || sz > strng.length) "" else {
//      strng.grouped(sz).filter(_.length == sz).map{ str =>
//        if (str.map(_ - 48).map(scala.math.pow(_, 3)).sum % 2 == 0) str.reverse
//        else str.tail + str.head
//      }.mkString("")
//    }
//  }
//}

//Good and concise, same as above only strings are manipulated
//However the code is not really super readable...
//object RevRot {
//  def revRot(strng: String, sz: Int): String = iter(strng, sz, "")
//
//  def iter(s: String, sz: Int, res: String): String = {
//    if (sz == 0 || s == "" || s.length < sz)
//      return res
//
//    var (text, next) = s.splitAt(sz)
//
//    text.sum % 2 match {
//      case 0 => iter(next, sz, res + text.reverse)
//      case _ => {
//        var (h, t) = text.splitAt(1)
//        iter(next, sz, res + t + h)
//      }
//    }
//  }
//}

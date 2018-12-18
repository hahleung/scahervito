object Rotations {
  // Design choice: the check is happening while permuting
  // Didn't want to waste computation to build all permutations first (uniq)
  // and then start checking the inclusion

  def containAllRots(string: String, array: List[String]): Boolean = {
    @annotation.tailrec
    def go(charList: List[Char], counter: Int, array: List[String]): Boolean = {
      if (counter == 0) true
      else if (array contains charList.mkString) {
        val h :: t = charList
        go(t ++ List(h), counter - 1, array)
      }
      else false
    }

    go(string.toList, string.length, array)
  }
}

//object Rotations {
//  def containAllRots(strng: String, arr: List[String]): java.lang.Boolean = {
//    val len = strng.length();
//    val allRotations = (1 to len).map(i => strng.substring(len - i).concat(strng.substring(0, len - i)))
//    allRotations.map(arr.contains(_)).reduceLeft(_ && _)
//  }
//}

//object Rotations {
//  def containAllRots(strng: String, arr: List[String]) = {
//    strng.scanLeft(strng)((s,_) => s.tail :+ s.head).forall(arr.contains)
//  }
//}

//object Rotations {
//  def containAllRots(s: String, arr: List[String]): java.lang.Boolean = {
//    var sSet = arr.toSet
//    (for(i <- Range(0, s.length)) yield (s.slice(i, s.length) + s.slice(0, i)))
//      .forall((ss) => sSet.contains(ss))
//  }
//}

//object Rotations {
//  def containAllRots(strng: String, arr: List[String]): java.lang.Boolean = {
//    val l = strng.toList
//    Seq.range(0,l.size)
//      .map(i =>  l.takeRight(i) ++ l.take(l.size-i))
//      .map(_.mkString)
//      .count(!arr.contains(_)) == 0
//  }
//}

//object Rotations {
//  def containAllRots(string: String, arr: List[String]): java.lang.Boolean = {
//    (0 until string.length).map(
//      i => string.substring(i, string.length) + string.substring(0, i)
//    ).distinct.diff(arr).isEmpty
//  }
//}

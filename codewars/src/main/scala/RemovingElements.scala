object Kata {
  def removeEveryOther[T](list: List[T]): List[T] = {
    list.zipWithIndex.filter(_._2 % 2 == 0).map(_._1)

    // Group by 2 and get the head
    // list.grouped(2).map(_.head).toList

    // Using for-comprehension mechanism
    // for {
    //   (x, i) <- list.zipWithIndex
    //   if i % 2 == 0
    // } yield x

    // Using pattern matching + recursion (not tail optimized)
    // list match {
    //   case Nil => Nil
    //   case x :: Nil => x :: Nil
    //   case x :: y :: tail => x :: removeEveryOther(tail)
    // }

    // Using stream
    // list
    //   .toStream
    //   .zipWithIndex
    //   .filter(x => x._2 % 2 == 0)
    //   .map(x => x._1)
    //   .toList
  }
}

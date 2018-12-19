object CountPositivesSumNegatives {
  def countPositivesSumNegatives(integers: Array[Int]): (Int, Int) = {
    def go(integers: Array[Int], countPositives: Int, sumNegatives: Int): (Int, Int) =
      integers.headOption match {
        case None => (countPositives, sumNegatives)
        case Some(head) if head < 0 => go(integers.tail, countPositives, sumNegatives + head)
        case Some(head) if head > 0 => go(integers.tail, countPositives + 1, sumNegatives)
        case Some(head) if head == 0 => go(integers.tail, countPositives, sumNegatives)
      }

    go(integers, 0, 0)
  }

  // Concise but it's going through the collection twice
  // def countPositivesSumNegatives(xs: Array[Int]): (Int, Int) =
  //   (xs.count(_ > 0), xs.filter(_ < 0).sum)

  // Elegant, partition is working pretty well here
  // def countPositivesSumNegatives(integers: Array[Int]): (Int, Int) =
  //   integers.partition(_ > 0) match { case (pos, neg) => (pos.size, neg.sum) }

  // Pretty elegant and efficient, the case == 0 is implicitly handled
  // def countPositivesSumNegatives(integers: Array[Int]): (Int, Int) = integers.foldLeft((0,0)){(acc,x) => if (x > 0) (acc._1+1,acc._2) else (acc._1,acc._2+x)}
}

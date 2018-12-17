object Runner {
  def variance(xs: Seq[Double]): Option[Double] = {
    def mean(collection: Seq[Double]): Option[Double] =
      collection.length match {
        case 0 => None
        case _ => Some(collection.sum / collection.length)
      }

    mean(xs).flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))
  }

  // Just a boilerplate to help
  def assert(subject: Any, expected: Any): Unit = {
    val result = subject == expected
    val display =
      result match {
        case true => "Passed\n\tReceived: " + subject.toString
        case false => "Failed\n\tReceived: " + subject.toString + "\n\tExpected: " + expected.toString
      }
    println(display)
  }

  def main(args: Array[String]): Unit = {
    val myList = List(1.0, 2.0, 3.0, 4.0)
    val emptyList = List()

    println("\n\tVariance")
    assert(variance(myList), Some(1.25))
    assert(variance(emptyList), None)
  }
}

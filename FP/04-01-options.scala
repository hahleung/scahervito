sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] =
    this match {
      case Some(a) => Some(f(a))
      case _ => None
    }

  def flatMap[B](f: A => Option[B]): Option[B] =
    this.map(f).getOrElse(None)
    // this match {
    //   case Some(a) => f(a)
    //   case _ => None
    // }

  def getOrElse[B>:A](default: => B): B =
    this match {
      case Some(a) => a
      case _ => default
    }

  def orElse[B>:A](ob: => Option[B]): Option[B] =
    this.map(x => Some(x)).getOrElse(ob)
    // this match {
    //   case Some(a) => this
    //   case _ => ob
    // }

  def filter(f: A => Boolean): Option[A] =
    this.flatMap(a => if (f(a)) Some(a) else None)
    // this match {
    //   case Some(a) if f(a) => this
    //   case _ => None
    // }
}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Runner {
  def assert(subject: Any, expected: Any): Unit = {
    val result = subject == expected
    val display =
      result match {
        case true => "Passed\n\tReceived: " + subject.toString
        case false => "Failed\n\tReceived: " + subject.toString + "\n\tExpected: " + expected.toString
      }
    println(display)
  }

  def main(args: Array[String]) : Unit = {
    val add2 = (x: Int) => x + 2
    println(add2(2))

    println("\n\tMap")
    val myOption: Option[Int] = Some(4)
    val myOptionNone: Option[Int] = None
    assert(myOption.map(add2), Some(6))
    assert(myOptionNone.map(add2), None)

    println("\n\tflatMap")
    val add3 = (x: Int) => Some(x + 3)
    assert(myOption.flatMap(add3), Some(7))
    assert(myOptionNone.flatMap(add3), None)

    println("\n\tgetOrElse")
    val default4: Int = 42
    assert(myOption.getOrElse(default4), 4)
    assert(myOptionNone.getOrElse(default4), 42)

    println("\n\torElse")
    val default5: Option[Int] = Some(42)
    assert(myOption.orElse(default5), Some(4))
    assert(myOptionNone.orElse(default5), Some(42))

    println("\n\tfilter")
    val filterFunction = (x: Int) => x == 4
    val myOption2: Option[Int] = Some(2)
    assert(myOption.filter(filterFunction), Some(4))
    assert(myOptionNone.filter(filterFunction), None)
    assert(myOption2.filter(filterFunction), None)
  }
}

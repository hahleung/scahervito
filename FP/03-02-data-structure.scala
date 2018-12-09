sealed trait MyList[+A] // `MyList` data type, parameterized on a type, `A`
case object Nil extends MyList[Nothing] // A `MyList` data constructor representing the empty MyList
/* Another data constructor, representing nonempty MyLists. Note that `tail` is another `MyList[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

object MyList { // `MyList` companion object. Contains functions for creating and working with MyLists.
  def sum(ints: MyList[Int]): Int = ints match { // A function that uses pattern matching to add up a MyList of integers
    case Nil => 0 // The sum of the empty MyList is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a MyList starting with `x` is `x` plus the sum of the rest of the MyList.
  }

  def product(ds: MyList[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): MyList[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](MyList: MyList[A]): MyList[A] =
    MyList match {
      case Cons(x, xs) => xs
      case whatever => Nil
    }

  // val x = MyList(1,2,3,4,5) match {
  //   case Cons(x, Cons(2, Cons(4, _))) => x
  //   case Nil => 42
  //   case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  //   case Cons(h, t) => h + sum(t)
  //   case _ => 101
  // }
}

object Runner {
  def main(args: Array[String]) : Unit = {
    // val x = Cons("a", Cons("b", Nil))

    val aList = MyList(1,2,3,4)
    val tailingList = MyList.tail(aList)
    println(tailingList)
  }
}

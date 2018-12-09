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

  val patternMatch = MyList(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def setHead[A](newHead: A, list: MyList[A]): MyList[A] =
    list match {
      case Cons(_head, tail) => Cons(newHead, tail)
      case _ => list
    }

  def drop[A](list: MyList[A], n: Int): MyList[A] =
    n match {
      case 0 => list
      case _ =>
        val newList = tail(list)
        drop(newList, n - 1)
    }

  def dropWhile[A](list: MyList[A], f: A => Boolean): MyList[A] =
    list match {
      case Cons(head, tail) =>
        f(head) match {
          case true => dropWhile(tail, f)
          case false => Cons(head, dropWhile(tail, f))
        }
      case _ => Nil
    }

  def goodDropWhile[A](list: MyList[A])(f: A => Boolean): MyList[A] =
    list match {
      case Cons(head, tail) if f(head) => dropWhile(tail, f)
      case _ => list
    }

  def init[A](list: MyList[A]): MyList[A] = {
    list match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(head, tail) => Cons(head, init(tail))
    }
  }
}

object Runner {
  def main(args: Array[String]) : Unit = {
    val testingCons = Cons("a", Cons("b", Nil))

    println(MyList.patternMatch)

    val aList = MyList(1,2,3,4)
    val tailTesting = MyList.tail(aList)
    println(tailTesting)

    val setHeadTesting = MyList.setHead(42, aList)
    println(setHeadTesting)

    val dropTesting = MyList.drop(aList, 2)
    println(dropTesting)

    val otherList = MyList("wow", "no", "wow", "yes")
    val wowFunction = (element: String) => element == "wow"
    val dropWhileTesting = MyList.dropWhile(otherList, wowFunction)
    println(dropWhileTesting)
    val goodDropWhileTesting = MyList.goodDropWhile(otherList)(x => x == "wow")
    println(goodDropWhileTesting)

    val initTesting = MyList.init(otherList)
    println(otherList)
  }
}

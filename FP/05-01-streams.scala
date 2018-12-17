// package fpinscala.laziness

import Stream._

trait Stream[+A] {
  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
    this match {
      case Cons(h, t) => f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
      case _ => z
    }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the stream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

  @annotation.tailrec
  final def find(f: A => Boolean): Option[A] = this match {
    case Empty => None
    case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
  }

  def toList(): List[A] =
    this match {
      case Cons(h, t) => h() :: t().toList()
      case _ => List()
    }

  def take(n: Int): Stream[A] =
  //    n match {
  //      case 0 => empty
  //      case n => this match {
  //        case Cons(h, t) => cons(h(), t().take(n - 1))
  //        case _ => empty
  //      }
  //    }
    this match {
      case Cons(h, t) if (n > 0) => cons(h(), t().take(n - 1))
      case Cons(_h, _t) if (n == 0) => empty
      case _ => empty
    }

  def drop(n: Int): Stream[A] =
    this match {
      case Cons(_h, t) if (n > 0) => t().drop(n - 1)
      case Cons(_h, _t) if (n == 0) => this
      case _ => empty
    }

  def takeWhile(p: A => Boolean): Stream[A] =
    this match {
      case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
      case _ => empty
    }

  def forAll(p: A => Boolean): Boolean =

  // def headOption: Option[A] = ???

  // 5.7 map, filter, append, flatmap using foldRight. Part of the exercise is
  // writing your own function signatures.

  // def startsWith[B](s: Stream[B]): Boolean = ???
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = Stream.cons(1, ones)

  // def from(n: Int): Stream[Int] = ???

  // def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = ???
}

object Runner {
  // Just a boilerplate to help
  def displayTitle(title: String): Unit = println("\n\t" + title)

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
    val myStream = Stream(1, 2, 3)
    val myEmptyStream = Stream()
    val isEven = (x: Int) => x % 2 == 0
    val isLowerThan3 = (x: Int) => x < 3
    val isLowerThan4 = (x: Int) => x < 4

    displayTitle("TO LIST")
    assert(myStream.toList, List(1, 2, 3))

    displayTitle("TAKE")
    assert(myEmptyStream.take(2).toList, List())
    assert(myStream.take(0).toList, List())
    assert(myStream.take(2).toList, List(1, 2))
    assert(myStream.take(3).toList, List(1, 2, 3))
    assert(myStream.take(4).toList, List(1, 2, 3))

    displayTitle("DROP")
    assert(myEmptyStream.drop(2).toList, List())
    assert(myStream.drop(0).toList, List(1, 2, 3))
    assert(myStream.drop(1).toList, List(2, 3))
    assert(myStream.drop(2).toList, List(3))
    assert(myStream.drop(3).toList, List())
    assert(myStream.drop(4).toList, List())

    displayTitle("TAKE WHILE")
    assert(myEmptyStream.takeWhile(isEven).toList, List())
    assert(myStream.takeWhile(isEven).toList, List())
    assert(myStream.takeWhile(isLowerThan3).toList, List(1, 2))

    displayTitle("FOR ALL")
    assert(myEmptyStream.forAll(isEven).toList, false)
    assert(myStream.forAll(isEven).toList, false)
    assert(myStream.forAll(isLowerThan4).toList, true)
  }
}

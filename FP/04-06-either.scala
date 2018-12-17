// package fpinscala.errorhandling

import scala.{Option => _, Either => _, Left => _, Right => _, _} // hide std library `Option` and `Either`, since we are writing our own in this chapter

sealed trait Either[+E,+A] {
  def map[B](f: A => B): Either[E, B] =
    this match {
      case Right(content) => Right(f(content))
      case Left(exception) => Left(exception)
    }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
    this match {
      case Right(content) => f(content)
      case Left(exception) => Left(exception)
    }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] =
    this match {
      case Right(content) => Right(content)
      case Left(_exception) => b
    }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    (this, b) match {
      case (Right(a), Right(content)) => Right(f(a, content))
      case (_, Left(expection)) => Left(expection)
      case (Left(expection), _) => Left(expection)
    }
    // for { a <- this; b1 <- b } yield f(a,b1)
}
case class Left[+E](value: E) extends Either[E,Nothing]
case class Right[+A](value: A) extends Either[Nothing,A]

object Either {
  def sequence[E,A](list: List[Either[E,A]]): Either[E,List[A]] = {
    def getList(eitherList: Either[E, List[A]]): List[A] =
      eitherList match {
        case Right(list) => list
        case Left(e) => List()
      }

    @annotation.tailrec
    def go(eitherList: List[Either[E, A]], list: Either[E, List[A]]): Either[E, List[A]] =
      eitherList match {
        case Left(e) :: xs => Left(e)
        case Right(a) :: xs => go(xs, Right(a :: getList(list)))
        case _ => list
      }

    go(list, Right(List())) match {
      case Right(unreversedList) => Right(unreversedList.reverse)
      case Left(e) => Left(e)
    }
  }

  def traverse[E,A,B](list: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
    def getList(eitherList: Either[E, List[B]]): List[B] =
      eitherList match {
        case Right(list) => list
        case Left(e) => List()
      }

    // First issue: can't use `.value` property on a Either
    // Second issue: Nil is not an acceptable an E exception
    def go(list: List[A], eitherList: Either[E, List[B]]): Either[E, List[B]] =
      list match {
        case x :: xs =>
          f(x) match {
            case Right(a) => Right(a :: getList(eitherList))
            case Left(e) => Left(e)
          }
        case _ => Left(Nil)
      }

    go(list, Right(List()))
  }

  def mean(xs: IndexedSeq[Double]): Either[String, Double] =
    if (xs.isEmpty)
      Left("mean of empty list!")
    else
      Right(xs.sum / xs.length)

  def safeDiv(x: Int, y: Int): Either[Exception, Int] =
    try Right(x / y)
    catch { case e: Exception => Left(e) }

  def Try[A](a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e) }
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

  def main(args: Array[String]) : Unit = {
    val eitherRight = Right("wow")
    val eitherLeft = Left("shit")
    val eitherDefault = Right("WOW WOW")
    val eitherList = List(eitherRight, eitherDefault)
    val eitherListWithShit = List(eitherRight, eitherLeft, eitherDefault)
    val superTransformation = (x: String) => 42
    val superEither = (x: String) => eitherDefault
    val transformation = (x: String, y: String) => "not sure"

    displayTitle("MAP")
    assert(eitherRight.map(superTransformation), Right(42))
    assert(eitherLeft.map(superTransformation), eitherLeft)

    displayTitle("FLAT MAP")
    assert(eitherRight.flatMap(superEither), eitherDefault)
    assert(eitherLeft.flatMap(superEither), eitherLeft)

    displayTitle("OR ELSE")
    assert(eitherRight.orElse(eitherDefault), eitherRight)
    assert(eitherLeft.orElse(eitherDefault), eitherDefault)

    displayTitle("MAP 2")
    assert(eitherRight.map2(eitherDefault)(transformation), Right("not sure"))
    assert(eitherLeft.map2(eitherDefault)(transformation), eitherLeft)

    displayTitle("SEQUENCE")
    assert(Either.sequence(eitherList), Right(List("wow", "WOW WOW")))
    assert(Either.sequence(eitherListWithShit), Left("shit"))

    displayTitle("TRAVERSE")
    val myList = List("wow", "no way")
    assert(
      Either.traverse(myList)((x: String) => Right(x.toUpperCase)),
      Right(List("wow", "WOW WOW"))
    )

    assert(Either.traverse(myList)((x: String) => Right(x.toUpperCase)), Left("shit"))
  }
}

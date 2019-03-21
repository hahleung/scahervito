package chapter4

sealed trait Calculator

final case class Success(result: Int) extends Calculator

final case class Failure(error: String) extends Calculator

object Calculator {
  def +(calculator: Calculator, int: Int): Calculator = calculator match {
    case Success(wow) => Success(wow + int)
    case Failure(bad) => Failure(bad)
  }

  def -(calculator: Calculator, int: Int): Calculator = calculator match {
    case Success(wow) => Success(wow - int)
    case Failure(bad) => Failure(bad)
  }

  def /(calculator: Calculator, int: Int): Calculator = calculator match {
    case Success(wow) => int match {
      case 0 => Failure("wow 0")
      case _ => Success(wow / int)
    }
    case Failure(bad) => Failure(bad)
  }
}

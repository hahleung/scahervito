// scala FP/underscore/chapter3/ChipShop.scala

case class Cat(name: String, colour: String, food: String)

case class ChipShop()

object ChipShop {
  def willServe(cat: Cat): Boolean =
    cat match {
      case Cat(_, _, "Chips") => true
      case Cat(_, _, _) => false
    }
}

object Runner {
  def main(args: Array[String]): Unit = {
    val cat = Cat("Felix", "red", "nochips")
    println(ChipShop.willServe(cat))
  }
}

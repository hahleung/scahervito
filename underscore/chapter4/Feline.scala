// scala FP/underscore/chapter4/Feline.scala
// p90

trait Feline {
  def name: String

  def colour: String

  def sound: String = "roar"
}

case class Cat(name: String, colour: String) extends Feline {
  override def sound = "meow"
}

case class Tiger(name: String) extends Feline {
  def colour = "red"
}

val cat = Cat("Felix", "black")
println(cat.sound)

val tiger = Tiger("Poo")
println(tiger.colour)
println(tiger.name)
println(tiger.sound)

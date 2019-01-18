// scala FP/underscore/chapter4/Shape.scala
// p91

trait Shape {
  def sides: Int
  def perimeter: Float
  def area: Float
}

case class Circle(radius: Float = 0F) {
  def sides = 0
  def perimeter = 2 * math.Pi * radius
  def area = math.Pi * math.pow(radius, 2)
}

val circle = Circle(3F)
println(circle.sides)
println(circle.perimeter)
println(circle.area)
// 0
// 18.84955592153876
// 28.274333882308138

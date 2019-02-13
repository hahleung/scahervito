// scala underscore/chapter4/Shape.scala
// p91

trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double = 0D) extends Shape {
  def sides = 0
  def perimeter = 2D * math.Pi * radius
  def area = math.Pi * math.pow(radius, 2)
}

val circle = Circle(3D)
println(circle.sides)
println(circle.perimeter)
println(circle.area)
// 0
// 18.84955592153876
// 28.274333882308138

trait RectangleComponent {
  def length: Double
  def width: Double

  def sides = 4
  def perimeter = 2 * (length + width)
  def area = length * width
}

case class Rectangle(length: Double, width: Double) extends Shape with RectangleComponent {
}

val rectangle = Rectangle(2D, 4D)
println(rectangle.sides)
println(rectangle.perimeter)
println(rectangle.area)
// 4
// 12.0
// 8.0

case class Square(side: Double) extends Shape with RectangleComponent {
  val length = side
  val width = side
}

val square = Square(2D)
println(square.sides)
println(square.perimeter)
println(square.area)
// 4
// 8.0
// 4.0

object Draw {
  def apply(shape: Shape) =
    shape match {
      case Rectangle(l ,w) => "oh wow"
    }
}

println(Draw(Rectangle(1.0, 3.0)))

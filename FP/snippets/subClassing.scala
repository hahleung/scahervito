class Point(xc: Int, yc: Int) {
  val x: Int = xc
  val y: Int = yc
  def move(dx: Int, dy: Int): Point =
    new Point(x + dx, y + dy)
}

class ColorPoint(u: Int, v: Int, c: String) extends Point(u, v) {
  val color: String = c
  def compareWith(pt: ColorPoint): Boolean =
    (pt.x == x) && (pt.y == y) && (pt.color == color)
  override def move(dx: Int, dy: Int): ColorPoint =
    new ColorPoint(x + dy, y + dy, color)
}

// val x = new ColorPoint(1, 2, "red")

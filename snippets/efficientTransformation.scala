case class Toto(x: Int, y: Int) {}

case class Totos(nToto: Option[Toto] = None, cToto: Option[Toto] = None, gToto: Option[Toto] = None) {
  def all = List(nToto, cToto, gToto)
}

def wow(a: Int, b: Int): Int = {
  println("jesus")
//  Thread.sleep(5000)
  a + 32 * b
}

val totos = List(Toto(1, 2), Toto(1, 2), Toto(4, 5), Toto(1, 2))

val slowTotos = totos.map(toto => wow(toto.x, toto.y))
println(slowTotos)

val fastTotos = totos.foldLeft((List.empty[Int], Map.empty[(Int, Int), Int])) { (x, toto) =>
  x._2.contains((toto.x, toto.y)) match {
    case true  =>
      val result = x._2.getOrElse((toto.x, toto.y), 0)
      (result :: x._1, x._2)
    case false =>
      val result = wow(toto.x, toto.y)
      (result :: x._1, x._2 + ((toto.x, toto.y) -> result))

  }
}
println(fastTotos._1.reverse)


//val x = Map("x" -> (1, 2), "y" -> (1, 2), "z" -> (3, 4))
//
//val s: List[(Int, Int)] = x.values.toSet.toList
//
//val l: List[Int] = s.map(tuple => tuple._1 + tuple._2)
//
//val y = Map((1, 2) -> "lol", (3, 4) -> "fe", (1, 2) -> "nein")
//y.keys.toSet

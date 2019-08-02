case class Toto(x: Int, y: Int, z: Option[Int] = None) {}

case class Totos(nToto: Option[Toto] = None, pToto: Option[Toto] = None, gToto: Option[Toto] = None) {}

def wow(a: Int, b: Int): Int = {
  println("jesus")
  //  Thread.sleep(5000)
  a + 32 * b
}

//val totos = List(Toto(1, 2), Toto(1, 2), Toto(4, 5), Toto(1, 2))
//
//val slowTotos = totos.map(toto => wow(toto.x, toto.y))
//println(slowTotos)

val totos = Totos(Some(Toto(1, 2)), Some(Toto(1, 2)), Some(Toto(2, 4)))
val totosMap = Map("n" -> totos.nToto, "p" -> totos.pToto, "g" -> totos.gToto)

def newLocalTotos(key: String, value: Option[Int], totos: Totos): Totos = key match {
  case "n" => totos.copy(nToto = totos.nToto.map(_.copy(z = value)))
  case "p" => totos.copy(pToto = totos.pToto.map(_.copy(z = value)))
  case "g" => totos.copy(gToto = totos.gToto.map(_.copy(z = value)))
}

val fastTotos = totosMap.foldLeft((totos, Map.empty[(Int, Int), Option[Int]])) { (totosAndCache, totoDict) =>
  val primaryKey = totoDict._1
  val maybeToto: Option[Toto] = totoDict._2
  val enhancedTotos = totosAndCache._1
  val localCache = totosAndCache._2

  maybeToto match {
    case None       => (enhancedTotos, localCache)
    case Some(toto) =>
      val totoKey = (toto.x, toto.y)
      localCache.contains(totoKey) match {
        case true  =>
          val result = localCache.getOrElse(totoKey, None)
          (newLocalTotos(primaryKey, result, enhancedTotos), localCache)
        case false =>
          val result = Some(wow(toto.x, toto.y))
          val newLocalCache = localCache + (totoKey -> result)
          (newLocalTotos(primaryKey, result, enhancedTotos), newLocalCache)

      }
  }
}

println(fastTotos)


//val x = Map("x" -> (1, 2), "y" -> (1, 2), "z" -> (3, 4))
//
//val s: List[(Int, Int)] = x.values.toSet.toList
//
//val l: List[Int] = s.map(tuple => tuple._1 + tuple._2)
//
//val y = Map((1, 2) -> "lol", (3, 4) -> "fe", (1, 2) -> "nein")
//y.keys.toSet

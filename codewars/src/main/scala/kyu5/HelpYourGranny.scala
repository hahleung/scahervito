package kyu5

object HelpYourGranny {
  def tour(friendsSteps: List[String], friendsTownsList: List[(String, String)], distanceMap: Map[String, Double]): Int = {
    val itineraries = getItinerary(friendsSteps, friendsTownsList)
    val getDistanceFromOriginWithMap = (town: String) => getDistanceFromOrigin(town, distanceMap)
    val getDistanceWithMap = (towns: (String, String)) => getDistance(towns, getDistanceFromOriginWithMap)

    val distances = itineraries.zip(itineraries.tail).map(getDistanceWithMap).sum

    val totalDistance = distances + getDistanceFromOriginWithMap(itineraries.head) + getDistanceFromOriginWithMap(itineraries.last)
    totalDistance.toInt
  }

  private def getItinerary(friendsSteps: List[String], friendsTownsList: List[(String, String)]): List[String] = {
    val friendsTownsMap = friendsTownsList.toMap
    val friendsTowns = friendsSteps.map(friendsTownsMap.get)
    getFriendsTowns(friendsTowns)
  }

  private def getFriendsTowns(townsList: List[Option[String]]): List[String] =
    townsList match {
      case Some(town) :: remainingTowns => town :: getFriendsTowns(remainingTowns)
      case _ => List()
    }

  private def getDistance(towns: (String, String), getDistanceFromOrigin: String => Double): Double = {
    val (firstTown, secondTown) = towns
    val originToFirstTown = getDistanceFromOrigin(firstTown)
    val originToSecondTown = getDistanceFromOrigin(secondTown)
    math.sqrt(math.pow(originToSecondTown, 2) - math.pow(originToFirstTown, 2))
  }

  private def getDistanceFromOrigin(town: String, distanceMap: Map[String, Double]): Double =
    distanceMap.getOrElse(town, 0.0)
}

// Not easily readable
//def tour(arrFriends: List[String], ftwns: List[(String, String)], h: Map[String, Double]): Int = {
//  def pif(c: Double, a: Double) = {
//  math.sqrt((c*c - a*a).abs)
//}
//  val frMap = ftwns toMap;
//  arrFriends.filter(frMap contains)
//  .map(frMap)
//  .map(h)
//  .:+(0.0)
//  .foldLeft((0.0, 0.0))((res, cur) => {
//  val d = pif(cur, res._2)
//  (res._1 + d, cur)
//})._1 toInt
//}

//def tour(arrFriends: List[String], ftwns: List[(String, String)], h: Map[String, Double]): Int = {
//  import Math.{pow, sqrt}
//  val square: Double => Double = pow(_, 2)
//  val ff = ftwns.toMap
//  val hh = h + ("X0" -> 0.0)
//  val towns = arrFriends.flatMap(ff.get)
//  ("X0" +: towns :+ "X0").sliding(2)
//  .map{case List(x1, x2) => sqrt((square(hh(x2)) - square(hh(x1))).abs)}
//  .sum
//  .floor
//  .toInt
//}

//def tour(arrFriends: List[String], ftwns: List[(String, String)], h: Map[String, Double]): Int = {
//  val townSequence = arrFriends map (f => ftwns.find( _._1 equals f) map(_._2)) flatten
//  val dist = townSequence map(h.get _) flatten
//  val zdist = dist zip dist.tail
//  floor (zdist map (t => sqrt( pow(t._2,2) - pow(t._1,2) )) sum ) + floor((dist head) + (dist last)) toInt
//}

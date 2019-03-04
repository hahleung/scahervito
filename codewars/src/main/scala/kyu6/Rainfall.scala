package kyu6

object Rainfall {
  def mean(town: String, data: String): Double = maybeStatistics(town, data, meanRainfalls)

  def variance(town: String, data: String): Double = maybeStatistics(town, data, varianceRainfalls)

  private def maybeStatistics(town: String, data: String, statFn: List[Double] => Double): Double =
    townRainfalls(town, data) match {
      case Left(_)          => -1.0
      case Right(rainfalls) => statFn(rainfalls)
    }

  private def meanRainfalls(rainfalls: List[Double]): Double = rainfalls.sum / rainfalls.size

  private def varianceRainfalls(rainfalls: List[Double]): Double = {
    val mean = meanRainfalls(rainfalls)
    rainfalls.foldLeft(0.0)((a, i) => a + math.pow(mean - i, 2)) / rainfalls.length
  }

  private def rainfalls(rawTownRainfalls: String): List[Double] =
    """[-+]?([0-9]*\.[0-9]+|[0-9]+)""".r.findAllIn(rawTownRainfalls).map(_.toDouble).toList

  private def townRainfalls(town: String, townsRainfalls: String): Either[String, List[Double]] =
    townsRainfalls.split("\n").find(_.contains(town + ":")) match {
      case None                   => Left("No records")
      case Some(rawTownRainfalls) => Right(rainfalls(rawTownRainfalls))
    }
}

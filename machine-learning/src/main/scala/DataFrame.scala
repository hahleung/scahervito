import scala.io.Source

case class NhsStat(ethnicity: String, time: String, typeOfRole: String, specificGrade: String, headCount: Int) {}

object NhsStat {
  def apply(nhsStatRaw: Array[String]) = new NhsStat(
    ethnicity = nhsStatRaw(1),
    time = nhsStatRaw(3),
    typeOfRole = nhsStatRaw(4),
    specificGrade = nhsStatRaw(7),
    headCount = nhsStatRaw(8).toInt
  )
}

object DataFrame extends App {
  def lineToData(line: String) = NhsStat(line.split("\",\""))

  val data: List[NhsStat] = Source
    .fromFile("src/main/resources/nhs-workforce.csv")
    .getLines()
    .toList
    .tail
    .map(lineToData)
    .filter(_.time == "as at Mar 2018")

  val allStats = data.filter(d => d.typeOfRole == "All" && d.ethnicity != "All known")
  println(allStats.size)
  println(allStats.foldRight(0)(_.headCount + _))

}

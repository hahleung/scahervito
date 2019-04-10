import scala.io.Source

case class NhsStat(ethnicity: String, typeOfRole: String, specificGrade: String, headCount: String) {}

object NhsStat {
  def apply(nhsStatRaw: Array[String]) = new NhsStat(
    ethnicity = nhsStatRaw(1),
    typeOfRole = nhsStatRaw(4),
    specificGrade = nhsStatRaw(7),
    headCount = nhsStatRaw(8)
  )
}

object DataFrame extends App {
  def lineToData(line: String) = NhsStat(line.split(","))

  val data = Source
    .fromFile("src/main/resources/nhs-workforce.csv")
    .getLines()
    .toList
    .tail
    .map(lineToData)

  println(data.head.headCount)

}

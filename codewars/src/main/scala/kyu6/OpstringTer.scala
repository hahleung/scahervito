package kyu6

object OpstringTer {

  def oper(f: String => String, s: String): String = f(s)

  def rot(s: String): String = highRotate(_.reverse.mkString, s).mkString("\n")

  def selfieAndRot(s: String): String = {
    val dots = List.fill(parseInput(s).length)(".").mkString
    val original = parseInput(s).map(_ + dots)
    val rotated = highRotate(dots + _.reverse.mkString, s)
    (original ++ rotated).mkString("\n")
  }

  private def parseInput(s: String): List[String] = s.split("\n").toList

  private def highRotate(f: String => String, s: String): List[String] = parseInput(s).map(f(_)).reverse
}

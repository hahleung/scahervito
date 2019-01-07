package kyu6

object OpstringsBis {
  // The dodgy names are imposed by the Kata.

  def oper(f: String => String, s: String): String = f(s)

  def diag2Sym(s: String): String = highTranspose(_.reverse.mkString, s).mkString("\n")

  def rot90Counter(s: String): String = highTranspose(_.mkString, s).mkString("\n")

  def selfieDiag2Counterclock(s: String): String = {
    (parseInput(s), highTranspose(_.reverse.mkString, s), highTranspose(_.mkString, s))
      .zipped
      .map({ case (a, b, c) => Array(a, b, c).mkString("|") })
      .mkString("\n")
  }

  private def parseInput(s: String): List[String] = s.split("\n").toList

  private def highTranspose(f: List[Char] => String, s: String): List[String] = parseInput(s).transpose.map(f(_)).reverse
}

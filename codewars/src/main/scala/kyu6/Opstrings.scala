package kyu6

object Opstrings {

  def oper(f: String => String, s: String): String = f(s)

  def diag1Sym(s: String): String = highTranspose(_.mkString, s).mkString("\n")

  def rot90Clock(s: String): String = highTranspose(_.reverse.mkString, s).mkString("\n")

  def selfieAndDiag1(string: String): String = {
    val original = parseInput(string)
    val diagonalized = highTranspose(_.mkString, string)
    original.zip(diagonalized).map(tupleToString).mkString("\n")
  }

  private def parseInput(s: String): List[String] = s.split("\n").toList

  private def highTranspose(f: List[Char] => String, s: String): List[String] = parseInput(s).transpose.map(f(_))

  private def tupleToString(tuple: (String, String)): String = {
    val (a, b) = tuple
    Array(a, b).mkString("|")
  }
}


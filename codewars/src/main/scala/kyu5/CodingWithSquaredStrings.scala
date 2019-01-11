package kyu5

object CodingWithSquaredStrings {

  def code(s: String): String = operate(s, s => OpsHelper.rot90Clock(squarify(s)))

  def decode(s: String): String = operate(s, s => desquarify(OpsHelper.rot90Counter(s)))

  private def operate(s: String, f: String => String): String = s match {
    case "" => "";
    case _ => f(s)
  }

  private def squarify(s: String): String = {
    val stringLength = s.length
    val squareLength = math.ceil(math.sqrt(stringLength)).toInt
    val char11Numbers = (math.pow(squareLength, 2) - stringLength).toInt
    val adjustedString = s + List.fill(char11Numbers)(11.toChar).mkString
    adjustedString.grouped(squareLength).mkString("\n")
  }

  private def desquarify(s: String): String =
    s.split("\n").mkString.replaceAll(11.toChar.toString, "")

}

object OpsHelper {

  def rot90Clock(s: String): String = highTranspose(_.reverse.mkString, s).mkString("\n")

  def rot90Counter(s: String): String = highTranspose(_.mkString, s).reverse.mkString("\n")

  private def parseInput(s: String): List[String] = s.split("\n").toList

  private def highTranspose(f: List[Char] => String, s: String): List[String] = parseInput(s).transpose.map(f(_))

}


// Elegant usage of .dropWhile( == 11.toChar)
//object Opstrings {
//
//  def code(s: String): String = {
//    val slen = s.length
//    if(slen == 0) return ""
//    val n = Math.sqrt(slen).ceil.toInt
//    (s + ('\u000b'.toString * (n*n-s.length))).grouped(n).toSeq.transpose.map(_.reverse.mkString).mkString("\n")
//
//  }
//
//  def decode(s: String): String = {
//    val slen = s.length
//    if(slen == 0) return ""
//    val n = Math.sqrt(slen).toInt
//    s.split("\n").toSeq.map(_.reverse.mkString).transpose.map(_.mkString).mkString.reverse.dropWhile(_ == 11.toChar).reverse
//  }
//}


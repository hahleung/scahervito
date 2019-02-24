package kyu7

object OrderedCountOfCharacters {
  def orderedCount(chars: String): List[(Char, Int)] =
    chars.foldLeft(Map.empty[Char, Int]) { (hashMap, char) =>
      val newCounter = hashMap.getOrElse(char, 0) + 1
      hashMap + (char -> newCounter)
    }.toList
}

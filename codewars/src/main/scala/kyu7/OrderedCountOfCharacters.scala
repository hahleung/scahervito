package kyu7

import scala.collection.mutable

object OrderedCountOfCharacters {
  def orderedCount(chars: String): List[(Char, Int)] =
    chars.foldLeft(mutable.LinkedHashMap.empty[Char, Int]) { (listMap, char) =>
      val newCounter = listMap.getOrElse(char, 0) + 1
      listMap += (char -> newCounter)
    }.toList
}

// Concise, but multiple passes to the input
//chars.distinct.map(c => (c, chars.count(_ == c))).toList

package kyu7

object SortArray {
  def sortByLength(array: Array[String]): Array[String] =
    array.sortBy(element => element.length)
}

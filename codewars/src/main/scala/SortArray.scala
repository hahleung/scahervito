object SortArray {
  def sortByLength(array: Array[String]): Array[String] =
    array.sortBy(element => element.size)
}

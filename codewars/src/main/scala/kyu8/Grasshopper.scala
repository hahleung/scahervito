package kyu8

object Grasshopper {
  def summation(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      n match {
        case 1 => acc + 1
        case n => go(n - 1, acc + n)
      }

    go(n, 0)
  }

  // def summation(n: Int): Int = (1 to n).sum
  // def summation(n: Int): Int = List.range(1, n+1).reduceLeft(_ + _)
  // def summation(n: Int): Int = (1 to n).reduceLeft( _ + _ )
  // def summation(n: Int): Int = n * (n + 1) / 2
}

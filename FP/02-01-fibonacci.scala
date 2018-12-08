object Fibonacci {
  def slowFib(n: Int): Int = {
    if (n <= 1) n
    else slowFib(n - 1) + slowFib(n - 2)
  }

  def main(args: Array[String]) : Unit = {
    val result = slowFib(5)
    println(result)
  }
}

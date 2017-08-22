def fibonacci(n: Int): Int = {
  def go(n: Int, fn: Int, fn1: Int): Int =
    if (n == 1) fn1
    else go(n-1, fn1, fn + fn1)

  go(n, 1, 1)
}

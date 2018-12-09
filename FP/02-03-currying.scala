def curry[A,B,C](f: (A,B) => C): A => (B => C) =
  (a: A) => ((b: B) => f(a,b))
  // With type inference:
  // a => (b => f(a, b))
  // Without parenthesis:
  // a => b => f(a, b)

def uncurry[A,B,C](f: A => B => C): (A, B) => C =
  // (a, b) => f(a, a => f(a))
  (a, b) => f(a)(b)

def compose[A,B,C](f: B => C, g: A => B): A => C =
  a => f(g(a))

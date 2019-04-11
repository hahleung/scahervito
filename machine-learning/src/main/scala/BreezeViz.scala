import breeze.linalg._
import breeze.plot._
import breeze.stats.distributions.Gaussian

object BreezeViz extends App {
  val f = Figure()
  val p = f.subplot(0)
  val x = linspace(0.0, 1.0)
  p += plot(x, x :^ 2.0)
  p += plot(x, x :^ 3.0, '.')
  p.xlabel = "x axis"
  p.ylabel = "y axis"
  f.saveas("lines.png")

  val p2 = f.subplot(2, 1, 1)
  val g: Gaussian = breeze.stats.distributions.Gaussian(0, 1)
  val r: IndexedSeq[Double] = g.sample(1000000)
  p2 += hist(r, 100)
  p2.title = "A normal distribution"
  f.saveas("subplots.png")
}

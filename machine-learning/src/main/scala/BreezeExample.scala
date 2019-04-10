import breeze.linalg.DenseMatrix

object BreezeExample extends App {
  val denseMatrix = DenseMatrix((1, 2), (3, 4))

  val denseMatrix2 = denseMatrix.t

  val matrixProduct = denseMatrix * denseMatrix2
  println(matrixProduct)
}

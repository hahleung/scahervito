package kyu6

case class ProductNaive(name: String, price: Float, quantity: Int) {
  def toUselessFormatting: String = {
    val formattedPrice = "$" ++ price.toInt.toString
    List(name, ">", "prx:", formattedPrice, "qty:", quantity).mkString(" ")
  }
}

object ProductNaive {
  def fromRawProduct(rawProduct: String): Option[ProductNaive] = {
    raw"<name>(.*?)</name><prx>(.*?)</prx><qty>(.*?)</qty>"
      .r
      .findAllIn(rawProduct)
      .matchData
      .map(data => ProductNaive(name = data.group(1), price = data.group(2).toFloat, quantity = data.group(3).toInt))
      .toList
      .headOption
  }

  def apply(name: String, price: Float, quantity: Int): ProductNaive = new ProductNaive(name, price, quantity)
}

case class CatalogNaive(rawCatalog: String) {
  val table: Map[String, ProductNaive] =
    rawCatalog
      .split("\n\n")
      .map(ProductNaive.fromRawProduct)
      .filter(_.isDefined)
      .map { maybeProduct: Option[ProductNaive] => (maybeProduct.get.name, maybeProduct.get) }
      .toMap

  def findProduct(product: String): String = {
    val products = table.filterKeys(_ contains product).values

    products.size match {
      case 0 => "Nothing"
      case _ => products.map(_.toUselessFormatting).mkString("\n")
    }
  }
}

object CatalogNaive {
  def catalog(rawCatalog: String, product: String): String = CatalogNaive(rawCatalog).findProduct(product)
}

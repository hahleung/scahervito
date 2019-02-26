package kyu6

case class Product(name: String, price: String, quantity: String) {
  def toUselessFormatting: String = List(name, ">", "prx:", "$" ++ price, "qty:", quantity).mkString(" ")
}

object Catalog {
  def catalog(rawCatalog: String, product: String): String =
    rawCatalog.split("\n\n").filter(_.contains(product)) match {
      case Array() => "Nothing"
      case array   => array.map(formatProduct).mkString("\n")
    }

  private def formatProduct(rawProduct: String): String =
    raw"<name>(.*?)</name><prx>(.*?)</prx><qty>(.*?)</qty>"
      .r
      .findAllIn(rawProduct)
      .matchData
      .map(data => Product(name = data.group(1), price = data.group(2), quantity = data.group(3)))
      .toList
      .head
      .toUselessFormatting
}


// Using XML scala in-built properties
//object Catalog {
//
//  def catalog(s: String, article: String): String = {
//    val x = scala.xml.XML.loadString("<root>" + s + "</root>")
//    val matching = x \ "prod" filter (e => ( (e \ "name" text) contains article))
//    if (matching isEmpty)
//      "Nothing"
//    else
//      matching map (e => (e \ "name" text) + " > prx: $" + (e \ "prx" text) + " qty: " + (e \ "qty" text) ) mkString "\n"
//  }
//}

// Better way to collect the patter matched groups
//object Catalog {
//  case class Entry(name: String, price: String, quantity: String) {
//    override def toString: String = {
//      s"${name} > prx: $$${price} qty: ${quantity}"
//    }
//  }
//
//  def catalog(s: String, article: String): String = {
//    val entryPattern = "<prod><name>(.+)</name><prx>([0-9.]+)</prx><qty>([0-9]+)</qty></prod>".r
//    val output = s.split("\n").map(_.trim)
//      .collect {
//        case entryPattern(name, price, qty) if name.contains(article) =>
//          Entry(name, price, qty)
//      }
//      .map(_.toString).mkString("\n")
//    if (output.isEmpty) "Nothing" else output
//  }
//}

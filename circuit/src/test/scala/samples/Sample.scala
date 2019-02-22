package samples

import circuit.FullCircuit
import io.circe.Decoder
import io.circe.parser.decode
import scala.io.Source.fromInputStream

object Sample {
  def loadJson[T: Decoder](path: String): T = {
    val json = fromInputStream(Sample.getClass.getResourceAsStream(path)).mkString

    decode[T](json) match {
      case Right(obj) => obj
      case Left(err)  => throw new RuntimeException(s"Error while decoding json from path: [$path]", err)
    }
  }

  object Circuit {
    val complex: FullCircuit = loadJson[FullCircuit]("/samples/circuit/complex.json")
  }

}

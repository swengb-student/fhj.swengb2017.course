package at.fhj.swengb.common

import java.nio.file.{Files, Path}

case class Csv(path: Path) {

  import scala.collection.JavaConverters._

  // precondition: entries must be enclosed by '"'
  def p(s: String): Boolean = s.startsWith("\"") && s.endsWith("\"")

  // replace strings if necessary
  def r(s: String): String = if (p(s)) s.substring(1, s.length - 1) else s

  val entries: Map[String, String] = {

    val res: Map[String, String] =
      (for {o <- Files.readAllLines(path).asScala
            arr = o.split(",")} yield arr match {
        case Array(a, b) if p(a) && p(b) =>
          val id = r(a)
          val github = r(b)
          println(s"$a,$b ::> $id -> ($github)")
          id -> github
        case _ => ???
      }).toMap

    res
  }

}

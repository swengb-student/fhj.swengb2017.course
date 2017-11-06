
package at.fhj.swengb.common

import java.awt.image.BufferedImage
import java.io._
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import javax.imageio.ImageIO

import scala.util.Try
import scala.util.control.NonFatal


/**
  * A namespace which contains functions to create files on disc.
  *
  * Also, creates parent directories if they don't exist for the given paths.
  */
object FileWriter extends CanLog {

  /**
    * Writes content to a file and ensures it gets _really_ written.
    *
    * @param path    the path to write to
    * @param content the content of the target file
    */
  private def toPath(path: Path, content: ByteBuffer): Try[Unit] = Try {
    time({
      val fous = new FileOutputStream(path.toFile)
      try {
        val c = fous.getChannel
        c.write(content)
        c.force(true)
        fous.flush()
        fous.getFD.sync()
      } finally {
        fous.close()
      }
    }, t => logTrace(s"Wrote file ${path.toAbsolutePath.toString} in $t ms."))
  }

  def toPath(path: Path, content: String): Try[Unit] = {
      toPath(path, ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)))
  }

  def toPath(path: Path, content: Array[Byte]): Try[Unit] =
    toPath(path, ByteBuffer.wrap(content))

  def toPath(path: Path, content: List[Array[Byte]]): Try[Unit] =
    toPath(path, ByteBuffer.wrap(content.flatten.toArray))


  /**
    * Creates a .bmp file on disc (and parent directories if needed) for the given buffered image.
    *
    * @param path  the path which denotes the image
    * @param image the image to persist
    * @return
    */
  def toPath(path: Path, image: BufferedImage): Boolean = {
    time({
      ImageIO.write(image, "bmp", path.toFile)
    }, t => logTrace(s"Wrote image ${path.toAbsolutePath} in $t ms."))
  }

  def printToFile(f: File)(op: BufferedWriter => Unit, withBOM: Boolean = false): Unit = {
    val fos = new FileOutputStream(f)

    if (withBOM) {
      fos.write(Array[Byte](0xEF.toByte, 0xBB.toByte, 0xBF.toByte))
    }
    val osw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"))
    try {
      op(osw)
      ()
    } catch {
      case NonFatal(e) =>
        logException(e.getMessage, e)
        sys.error(e.getMessage)
    } finally {
      if (osw != null) {
        osw.flush()
        osw.close()
        if (fos != null) {
          fos.close()
        }
      }
    }
  }

}
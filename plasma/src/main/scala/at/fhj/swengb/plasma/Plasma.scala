package at.fhj.swengb.plasma

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.image.{PixelFormat, PixelWriter}
import javafx.scene.{Group, Scene}
import javafx.stage.Stage

import scala.util.Random

/**
  * Simple sinus drawing on a byte array which is displayed via javafx.
  *
  * Created by lad on 14/07/2017.
  */
object Plasma {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[PlasmaJfxApp], args: _*)
  }

}

class PlasmaJfxApp extends javafx.application.Application {

  val (width, height) = (800, 600)

  val (xs, ys) = (0 until width, 0 until height)

  val sinTable: Array[Double] = (0 until 180).map(a => Math.sin(a * Math.PI / 180)).toArray

  val (xFac: Double, yFac: Double) = (sinTable.length.toDouble / width, sinTable.length.toDouble * height)


  /**
    * Black it out at the start
    */
  val a = Array.tabulate(width * height * 3)(i => 0.toByte)

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Plasma")

    val root = new Group()
    val canvas = new Canvas(width, height)

    root.getChildren.add(canvas)
    primaryStage.setScene(new Scene(root))
    primaryStage.show()


    new AnimationTimer() {


      override def handle(now: Long): Unit = {
        applySinus(a)
        drawByteArray(canvas, a)

      }
    }.start()


  }

  private def applySinus(backingArray: Array[Byte],
                         sinTable: Array[Double] = sinTable,
                         colorCount: Int = 255) = {
    for {x <- xs
         y <- ys} {
      val sinIndex = Math.min(sinTable.length - 1, (x * xFac).toInt)
      val c: Int = (sinTable(sinIndex) * colorCount).toInt
      backingArray(x * 3 + 0 + y * width * 3) = c.toByte
      backingArray(x * 3 + 1 + y * width * 3) = c.toByte
      backingArray(x * 3 + 2 + y * width * 3) = c.toByte
    }
  }

  private def applyNoise(a: Array[Byte]) = {
    for (i <- a.indices) a(i) = Random.nextInt(254).toByte
  }

  private def drawByteArray(canvas: Canvas, bytes: Array[Byte]) = {
    val pxw: PixelWriter = canvas.getGraphicsContext2D.getPixelWriter
    pxw.setPixels(0, 0, width, height, PixelFormat.getByteRgbInstance, bytes, 0, width * 3)
  }
}
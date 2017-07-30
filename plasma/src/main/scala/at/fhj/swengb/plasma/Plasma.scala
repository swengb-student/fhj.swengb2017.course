package at.fhj.swengb.plasma

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.image.{PixelFormat, PixelWriter}
import javafx.scene.{Group, Scene}
import javafx.stage.Stage

import scala.util.Random

/**
  * Created by lad on 14/07/2017.
  */
object Plasma {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[PlasmaJfxApp], args: _*)
  }

}

class PlasmaJfxApp extends javafx.application.Application {

  val (width, height, color) = (800, 600, 255 * 255 * 255)

  val (xs, ys, zs) = (0 to width, 0 to height, 0 until color)

  val a = Array.tabulate(width * height * 3)(i => {
    i % 3 match {
      case 0 => Random.nextInt(254).toByte
      case 1 => Random.nextInt(254).toByte
      case 2 => Random.nextInt(254).toByte
    }
  })

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Plasma")

    val root = new Group()
    val canvas = new Canvas(width, height)

    root.getChildren.add(canvas)
    primaryStage.setScene(new Scene(root))
    primaryStage.show()


    new AnimationTimer() {
      override def handle(now: Long): Unit = {

        for(i <- a.indices) a(i) = Random.nextInt(254).toByte

        drawByteArray(canvas, a)

      }
    }.start()


  }


  private def drawByteArray(canvas : Canvas, bytes : Array[Byte]) = {
    val pxw: PixelWriter = canvas.getGraphicsContext2D.getPixelWriter
    pxw.setPixels(0, 0, width, height, PixelFormat.getByteRgbInstance, bytes, 0, width * 3)
  }
}
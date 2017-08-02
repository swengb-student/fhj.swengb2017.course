package at.fhj.swengb.plasma

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.image.{PixelFormat, PixelWriter}
import javafx.scene.{Group, Scene}
import javafx.stage.Stage

import scala.collection.immutable
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

  /**
    * the width and height of our visual area
    */
  val (width, height) = (450, 150)

  /**
    * we have 3 color components, thus one line in our backing array is three times as long as the visual 'pixel' area
    */
  val backingArrayWidth = width * 3

  /**
    * ranges for x and y direction
    */
  val (ixs, iys) = (0 until width, 0 until height)

  val (xs, ys) = (ixs.map(_ * 3), iys.map(_ * backingArrayWidth))

  val xPiFac: Double = 2 * Math.PI / width.toDouble
  val yPiFac: Double = 2 * Math.PI / height.toDouble

  val colXs: immutable.IndexedSeq[Double] = ixs.map(x => xPiFac * x - Math.PI)

  val colYs: immutable.IndexedSeq[Double] = iys.map(y => yPiFac * y - Math.PI)

  /**
    * Black visual area out at the start of the program
    */
  val backingArray = Array.tabulate(width * height * 3)(i => 0.toByte)


  val colorDepth = 128

  /**
    * precompute sin values to gain some speed (presumably, didn't really measure)
    */
  val sinTable: Array[Double] = {
    (0 until 180).map(a => Math.sin(a * Math.PI / 180)).toArray
  }

  /**
    * precomputed cosinus values, reversing cos not to get negative values at a later computation stage
    */
  val cosTable: Array[Double] = {
    val vs = (0 until 90).map(a => Math.cos(a * Math.PI / 180)).toArray
    vs ++ vs.reverse
  }

  /**
    * scale visual area index to sintable
    */
  val (xFac: Double, yFac: Double) = (sinTable.length.toDouble / width, sinTable.length.toDouble / height)

  val state: Array[Double] = Array(0.0, 0.0)


  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Plasma")

    val root = new Group()
    val canvas = new Canvas(width, height)

    root.getChildren.add(canvas)
    primaryStage.setScene(new Scene(root))
    primaryStage.show()


    new AnimationTimer() {


      override def handle(now: Long): Unit = {
        applySinus(backingArray, sinTable, cosTable, colorDepth, state)
        drawByteArray(canvas, backingArray)

      }
    }.start()


  }

  private def applySinus(backingArray: Array[Byte]
                         , sinTable: Array[Double]
                         , cosTable: Array[Double]
                         , colorDepth: Int
                         , state: Array[Double]) = {
    val time = state(0)
    state(0) = state(0) + xPiFac

    val barCount = 5

    for {(x, colX) <- xs zip colXs
         (y, colY) <- ys zip colYs} {

      val v: Int = {

        // wandering bars from right to left
        val v1 = sin(colX * 10 + time * 10)

        // wobbling bars round each other
        val v2 = sin(barCount * (colX * sin(barCount * time / 2) + colY * cos(barCount * time / 3)) + barCount * time)

        // circles floating around
        val cx = colX + 0.5 * sin( time / 5)
        val cy = colY + 0.5 * cos( time / 3)
        val v3 = sin(Math.sqrt(100 * (cx * cx + cy * cy) + 1 +  time))

        // adding it all together
        val v = (v1 + v2 + v3) % 1

        val r = (v * colorDepth + 127).toInt
        if (r == -1) 0 else r
      }


      backingArray(x + 0 + y) = v.toByte
      backingArray(x + 1 + y) = v.toByte
      backingArray(x + 2 + y) = v.toByte
    }

  }

  //def sin(a: Double): Double = sinTable((a % sinTable.length).toInt)
  def sin(a: Double): Double = Math.sin(a)

  //def cos(a: Double): Double = cosTable((a % cosTable.length).toInt)
  def cos(a: Double): Double = Math.cos(a)

  private def computeSinIndex(time: Int, x: Double, y: Double) = {

  }

  private def drawByteArray(canvas: Canvas, bytes: Array[Byte]) = {
    val pxw: PixelWriter = canvas.getGraphicsContext2D.getPixelWriter
    pxw.setPixels(0, 0, width, height, PixelFormat.getByteRgbInstance, bytes, 0, backingArrayWidth)
  }

}
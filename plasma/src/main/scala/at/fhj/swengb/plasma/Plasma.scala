package at.fhj.swengb.plasma

import at.fhj.swengb.plasma.jfx.PlasmaJfxApp
import _root_.javafx.application.Application

/**
  * Old school graphic effect ('plasma') which is diplayed via java fx.
  *
  * Created by lad on 14/07/2017.
  */
object Plasma {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[PlasmaJfxApp], args: _*)
  }

}





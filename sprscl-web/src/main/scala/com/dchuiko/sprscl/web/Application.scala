package com.dchuiko.sprscl.web

object Application extends App {
  override def main(args: Array[String]): Unit = {
    EmbeddedJetty.start()
  }
}

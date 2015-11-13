package com.dchuiko.sprscl.web

import org.springframework.context.support.ClassPathXmlApplicationContext

object Application extends App {
  override def main(args: Array[String]): Unit = {
    val context = new ClassPathXmlApplicationContext("web-application-context.xml")

  }
}

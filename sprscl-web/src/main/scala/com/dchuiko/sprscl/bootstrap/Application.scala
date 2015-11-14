package com.dchuiko.sprscl.bootstrap

import com.dchuiko.sprscl.bootstrap.config.JettyConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object Application extends App {
  override def main(args: Array[String]): Unit = {
//    val context = new ClassPathXmlApplicationContext("web-application-context.xml")
    val context = new AnnotationConfigApplicationContext(classOf[JettyConfig]);
  }
}

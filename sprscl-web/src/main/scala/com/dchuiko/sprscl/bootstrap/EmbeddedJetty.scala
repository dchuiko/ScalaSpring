package com.dchuiko.sprscl.bootstrap

import com.typesafe.scalalogging.slf4j.Logger
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.web.context.support.XmlWebApplicationContext
import org.springframework.web.context.{ContextLoaderListener, WebApplicationContext}
import org.springframework.web.servlet.DispatcherServlet

object EmbeddedJetty {
  private val logger = Logger(LoggerFactory.getLogger(EmbeddedJetty.getClass))

  private val DEFAULT_PORT = 8080
  private val CONTEXT_PATH = "/"
  private val MAPPING_URL = "/*"

  def start() = {
    val jettyServer = new Server(DEFAULT_PORT)
    jettyServer.setHandler(getServletContextHandler(context()))

    try {
      logger.info("Stating server")
      jettyServer.start()


      jettyServer.join()
    } catch {
      case e: Throwable => logger.error("Unhandled error", e)
    } finally {
      jettyServer.destroy()
      logger.info("Server stopped")
    }
  }

  private def getServletContextHandler(context: WebApplicationContext): ServletContextHandler = {
    //    val contextHandler = new ServletContextHandler()
    val contextHandler = new WebAppContext
    contextHandler.setDescriptor("/webapp/WEB-INF/web.xml")

    contextHandler.setErrorHandler(null)
    contextHandler.setContextPath(CONTEXT_PATH)
    contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL)
    contextHandler.addEventListener(new ContextLoaderListener(context))
    contextHandler.setResourceBase(new ClassPathResource("webapp").getURI.toString)
    contextHandler
  }

  private def context(): WebApplicationContext = {
    val context: WebApplicationContext = new XmlWebApplicationContext()

    //    val context = new AnnotationConfigWebApplicationContext()
    //    context.setConfigLocation(CONFIG_LOCATION)
    //    context.getEnvironment.setDefaultProfiles(DEFAULT_PROFILE)
    context
  }
}

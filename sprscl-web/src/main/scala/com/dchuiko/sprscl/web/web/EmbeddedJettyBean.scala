package com.dchuiko.sprscl.web.web

import javax.annotation.PostConstruct

import com.typesafe.scalalogging.slf4j.Logger
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.LoggerFactory
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.context.support.XmlWebApplicationContext
import org.springframework.web.context.{ContextLoaderListener, WebApplicationContext}
import org.springframework.web.servlet.DispatcherServlet

@Component
class EmbeddedJettyBean {
  private val logger = Logger(LoggerFactory.getLogger(classOf[EmbeddedJettyBean]))

  private val DEFAULT_PORT = 8080
  private val CONTEXT_PATH = "/"
  private val CONFIG_LOCATION = "com.dchuiko.sprscl.web.config"
  private val MAPPING_URL = "/*"
  private val DEFAULT_PROFILE = "dev"

  @PostConstruct
  def init() = {
    val jettyServer = new Server(DEFAULT_PORT)
    jettyServer.setHandler(getServletContextHandler(null))

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
//    contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL)
//    contextHandler.addEventListener(new ContextLoaderListener(context))
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

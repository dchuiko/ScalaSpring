package com.dchuiko.sprscl.bootstrap

import javax.annotation.PostConstruct

import com.typesafe.scalalogging.slf4j.Logger
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.XmlWebApplicationContext

@Component
class EmbeddedJettyBean {
  private val logger = Logger(LoggerFactory.getLogger(classOf[EmbeddedJettyBean]))

  @Value("${port}")
  private val DEFAULT_PORT = 0
  @Value("${contextPath}")
  private val CONTEXT_PATH : String = ""
  @Value("${mappingUrl}")
  private val MAPPING_URL = ""

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

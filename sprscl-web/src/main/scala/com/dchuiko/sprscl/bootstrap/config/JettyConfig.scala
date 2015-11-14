package com.dchuiko.sprscl.bootstrap.config

import com.dchuiko.sprscl.bootstrap.EmbeddedJettyBean
import org.springframework.context.annotation.{Bean, Configuration, Profile}
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.{ClassPathResource, Resource}

@Configuration
class JettyConfig {
  @Bean
  def embeddedJetty() = new EmbeddedJettyBean
}

object JettyConfig {
  val DEV_PROPERTIES = Array[Resource](new ClassPathResource("jetty-dev.properties"))
  val TEST_PROPERTIES = Array[Resource](new ClassPathResource("jetty-test.properties"))
  val PROD_PROPERTIES = Array[Resource](new ClassPathResource("jetty-prod.properties"))

  @Profile(Array("dev"))
  class DevConfig {
    @Bean
    def propertySourcesPlaceholderConfigurer() = {
      val pspc = new PropertySourcesPlaceholderConfigurer()
      pspc.setLocations(JettyConfig.DEV_PROPERTIES: _*)
      pspc
    }
  }

  @Profile(Array("test"))
  class TestConfig {

    @Bean
    def propertySourcesPlaceholderConfigurer() = {
      val pspc = new PropertySourcesPlaceholderConfigurer()
      pspc.setLocations(JettyConfig.TEST_PROPERTIES: _*)
      pspc
    }
  }

  @Profile(Array("prod"))
  class ProdConfig {
    @Bean
    def propertySourcesPlaceholderConfigurer() = {
      val pspc = new PropertySourcesPlaceholderConfigurer()
      pspc.setLocations(JettyConfig.PROD_PROPERTIES: _*)
      pspc
    }
  }

}

package com.dchuiko.sprscl.web.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, WebMvcConfigurerAdapter}

@Configuration
@EnableWebMvc
class WebMvcConfig extends WebMvcConfigurerAdapter {
  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    super.addResourceHandlers(registry)
  }
}

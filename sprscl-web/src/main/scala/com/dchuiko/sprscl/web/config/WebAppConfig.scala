package com.dchuiko.sprscl.web.config

import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@ComponentScan(basePackages = Array("com.dchuiko.sprscl.beans", "com.dchuiko.sprscl.web"))
@EnableWebMvc
class WebAppConfig {
}


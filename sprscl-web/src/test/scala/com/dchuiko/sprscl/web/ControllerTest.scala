package com.dchuiko.sprscl.web

import com.dchuiko.sprscl.web.config.WebAppConfig
import com.dchuiko.sprscl.web.controller.IndexController
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}

@RunWith(classOf[SpringJUnit4ClassRunner])
@ActiveProfiles(Array("test"))
@WebAppConfiguration
@ContextConfiguration(classes = Array(classOf[WebAppConfig]))
class ControllerTest {
  @Autowired
//  @BeanProperty
  var myBean:IndexController = null

  @Test
  def test1() = {
    val a = 0
    val b = a
    assertNotNull(myBean)
  }
}

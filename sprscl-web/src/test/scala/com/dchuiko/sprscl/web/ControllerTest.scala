package com.dchuiko.sprscl.web

import com.dchuiko.sprscl.web.controller.IndexController
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("classpath:webapp/WEB-INF/applicationContext.xml"))
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

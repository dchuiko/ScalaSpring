package com.dchuiko.sprscl

import com.dchuiko.sprscl.back.beans.MyBean
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class MyBeanTest extends BaseTest {

  @Autowired
//  @BeanProperty
  var myBean:MyBean = null

  @Test
  def testMyBean() = {
    assertNotNull(myBean)
  }
}

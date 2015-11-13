package com.dchuiko.sprscl

import com.dchuiko.sprscl.beans.MyBean
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

//import static org.junit.Assert.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=CDPlayerConfig.class)
//public class CDPlayerTest {
//  @Autowired
//  private CompactDisc cd;
//  @Test
//  public void cdShouldNotBeNull() {
//    assertNotNull(cd);
//  }
//}

//@RunWith(classOf[SpringJUnit4ClassRunner])
//@ContextConfiguration(locations = Array("classpath:application-context.xml"))
class MyBeanTest extends BaseTest {

  @Autowired
//  @BeanProperty
  var myBean:MyBean = null

  @Test
  def testMyBean() = {
    assertNotNull(myBean)
  }
}

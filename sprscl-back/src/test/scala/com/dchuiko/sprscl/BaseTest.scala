package com.dchuiko.sprscl

import com.dchuiko.sprscl.back.config.BackAppConifg
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[BackAppConifg]))
abstract class BaseTest {

}

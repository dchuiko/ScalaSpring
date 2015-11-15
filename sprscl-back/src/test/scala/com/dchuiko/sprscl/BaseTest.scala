package com.dchuiko.sprscl

import com.dchuiko.sprscl.back.config.BackAppConfig
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}

@RunWith(classOf[SpringJUnit4ClassRunner])
@ActiveProfiles(Array("test"))
@ContextConfiguration(classes = Array(classOf[BackAppConfig]))
abstract class BaseTest {

}

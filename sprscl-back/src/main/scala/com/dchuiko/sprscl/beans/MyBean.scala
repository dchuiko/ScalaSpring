package com.dchuiko.sprscl.beans

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyBean @Autowired() (var b : Bean2) {

  def this (b : Bean2, s : String) = {
    this(b)
  }


}

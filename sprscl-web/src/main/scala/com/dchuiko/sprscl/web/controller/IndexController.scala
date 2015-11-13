package com.dchuiko.sprscl.web.controller

import org.springframework.stereotype.{Component, Controller}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

@Controller
class IndexController {

  @RequestMapping(path = Array("/**"), method = Array(RequestMethod.GET))
  @ResponseBody
  def showIndex(): String = {
    "Hello World!"
  }

}

//@Controller
//@SuppressWarnings("UnusedDeclaration")
//public class IndexController {
//
//    @Value("${example.message}")
//    private String message;
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @ResponseBody
//    public String showIndex() {
//        return message;
//    }
//
//}

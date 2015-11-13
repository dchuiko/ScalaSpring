package com.dchuiko.sprscl.web.filter

import javax.servlet._

import com.typesafe.scalalogging.slf4j.Logger
import org.slf4j.LoggerFactory

class TestFilter extends Filter {
  private val logger = Logger(LoggerFactory.getLogger(classOf[TestFilter]))

  override def init(filterConfig: FilterConfig): Unit = {
    logger.info("DO INIT TEST FILTER")
  }

  override def doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain): Unit = {
    logger.info("DO TEST FILTER!!")
    filterChain.doFilter(servletRequest, servletResponse)
  }

  override def destroy(): Unit = {}
}

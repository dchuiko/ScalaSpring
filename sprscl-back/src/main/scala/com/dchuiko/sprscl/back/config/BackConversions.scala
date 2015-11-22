package com.dchuiko.sprscl.back.config

import java.sql.ResultSet

import org.springframework.jdbc.core.RowMapper

object BackConversions {
  implicit def toRowMapper[T](function2: (ResultSet, Int) => T): RowMapper[T] = {
    new RowMapper[T] {
      override def mapRow(rs: ResultSet, rowNum: Int): T = function2(rs, rowNum)
    }
  }
}

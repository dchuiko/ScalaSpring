package com.dchuiko.sprscl.back.config

import java.sql.ResultSet
import java.util.Optional

import org.springframework.jdbc.core.RowMapper

object BackConversions {
  implicit def toRowMapper[T](function2: (ResultSet, Int) => T): RowMapper[T] = {
    new RowMapper[T] {
      override def mapRow(rs: ResultSet, rowNum: Int): T = function2(rs, rowNum)
    }
  }

  implicit def toOption[T](opt: Optional[T]): Option[T] = if (opt.isPresent) Some(opt.get()) else None

}

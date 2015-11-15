package com.dchuiko.sprscl

import java.sql.ResultSet
import javax.inject.Inject

import com.dchuiko.sprscl.back.config.BackAppConfig._
import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate

import scala.collection.JavaConversions._

class ConnectionTest extends BaseTest {
  @Inject
  val jdbc: JdbcTemplate = null

  @Test
  def testConnection = {
    val o: Seq[Int] = jdbc.query("select * from pg_stat_activity", (rs: ResultSet, rowNum: Int) => {
      rs.getInt(3)
    })

    val b = o

//    val d = 43.9
//    val x = d / "dfdf"
  }
}

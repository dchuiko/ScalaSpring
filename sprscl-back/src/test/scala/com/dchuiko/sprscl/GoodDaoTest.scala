package com.dchuiko.sprscl

import com.dchuiko.sprscl.back.dao.GoodDao
import org.junit.Assert.assertTrue
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class GoodDaoTest extends BaseTest {
  @Autowired
  var goodDao: GoodDao = _

  @Test
  def testCreate() = {
    val id = goodDao.create("aaa", BigDecimal(100))

    assertTrue(goodDao.find(id).isDefined)
  }
}

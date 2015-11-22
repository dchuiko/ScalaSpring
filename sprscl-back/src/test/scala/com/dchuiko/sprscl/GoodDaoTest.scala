package com.dchuiko.sprscl

import com.dchuiko.sprscl.back.dao.GoodDao
import org.junit.Assert._
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class GoodDaoTest extends BaseTest {
  @Autowired
  var goodDao: GoodDao = _

  @Test
  def testCreate() = {
    val id = goodDao.create("aaa", BigDecimal(100))

    var good = goodDao.find(id).get
    assertEquals("aaa", good.getName)

    good.setName("bbb")
    goodDao.update(good)

    good = goodDao.find(id).get
    assertNotNull(good)
    assertEquals("bbb", good.getName)
    assertEquals(1, good.getVersion)

    goodDao.update(id, "ccc", BigDecimal(200), good.getVersion)
    good = goodDao.find(id).get
    assertEquals("ccc", good.getName)
    assertEquals(2, good.getVersion)
  }
}

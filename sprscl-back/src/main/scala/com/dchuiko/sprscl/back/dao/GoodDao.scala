package com.dchuiko.sprscl.back.dao

import java.util.UUID

import com.dchuiko.sprscl.back.config.BackConversions._
import com.dchuiko.sprscl.back.db.model.tables.Good.GOOD
import com.dchuiko.sprscl.back.db.model.tables.records.GoodRecord
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
class GoodDao @Autowired() (val dsl : DSLContext) {

  def create(name: String, price: BigDecimal) : UUID = {
//    good.id
//    dsl.insertInto(Good, )
    val id: UUID = UUID.randomUUID()
    dsl.insertInto(GOOD, GOOD.ID, GOOD.NAME, GOOD.PRICE).values(id, name, price.bigDecimal).execute()
    id
  }

  def update(good: GoodRecord) : Unit = {
    good.update()
  }

  def update(id: UUID, name: String, price: BigDecimal, version: Int) = {
    dsl.update(GOOD)
      .set(GOOD.NAME, name)
      .set(GOOD.PRICE, price.bigDecimal)
      .set(GOOD.VERSION, Integer.valueOf(version + 1))
      .where(GOOD.ID.equal(id)).execute()
  }

  def find(id : UUID) : Option[GoodRecord] = {
    dsl.selectFrom(GOOD).where(GOOD.ID.equal(id)).fetchOptional()
  }
}

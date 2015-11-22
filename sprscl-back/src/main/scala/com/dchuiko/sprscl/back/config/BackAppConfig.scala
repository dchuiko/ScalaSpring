package com.dchuiko.sprscl.back.config

import javax.sql.DataSource

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import org.jooq
import org.jooq.impl.{DataSourceConnectionProvider, DefaultConfiguration, DefaultDSLContext}
import org.jooq.{DSLContext, SQLDialect}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, Profile}
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.{ClassPathResource, Resource}
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.{DataSourceTransactionManager, TransactionAwareDataSourceProxy}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.dchuiko.sprscl.back"))
class BackAppConfig {

  @Value("${hikari.url}")
  val hikariUrl: String = ""
  @Value("${hikari.user}")
  val hikariUser: String = ""
  @Value("${hikari.password}")
  val hikariPassword: String = ""

  @Bean
  def hikariConfig(): HikariConfig = {
    val hikariConfig = new HikariConfig()
    hikariConfig.setJdbcUrl(hikariUrl)
    hikariConfig.setUsername(hikariUser)
    hikariConfig.setPassword(hikariPassword)

    hikariConfig
  }

  @Bean
  def hikariDataSource(config: HikariConfig): DataSource = {
    new HikariDataSource(config)
  }

  @Bean
  def jdbcTemplate(dataSource: DataSource): JdbcTemplate = {
    new JdbcTemplate(dataSource)
  }

  @Bean
  def transactionManager(dataSource: DataSource): PlatformTransactionManager = {
    new DataSourceTransactionManager(dataSource)
  }

//  @Bean
//  def transactionAwareDataSource(dataSource: DataSource): TransactionAwareDataSourceProxy = {
//    new TransactionAwareDataSourceProxy(dataSource)
//  }

  @Bean
  def jooqDataSourceConnectionProvider(dataSource: DataSource): DataSourceConnectionProvider = {
    new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource))
  }

  @Bean
  def jooqConfiguration(jooqDataSourceConnectionProvider: DataSourceConnectionProvider): jooq.Configuration = {
    val jooqConfig: DefaultConfiguration = new DefaultConfiguration()
    jooqConfig.setConnectionProvider(jooqDataSourceConnectionProvider)
    jooqConfig.setSQLDialect(SQLDialect.POSTGRES_9_4)
    jooqConfig.settings().setExecuteWithOptimisticLocking(true)
    jooqConfig
  }

  @Bean
  def jooqDsl(jooqConfig : jooq.Configuration): DSLContext = {
    new DefaultDSLContext(jooqConfig)
  }
}

object BackAppConfig {
  val DEV_PROPERTIES = Array[Resource](new ClassPathResource("back-dev.properties"))
  val TEST_PROPERTIES = Array[Resource](new ClassPathResource("back-test.properties"))
  val PROD_PROPERTIES = Array[Resource](new ClassPathResource("back-prod.properties"))

  @Profile(Array("dev"))
  class DevConfig {
    @Bean
    def propertySourcesPlaceholderConfigurer() = {
      val pspc = new PropertySourcesPlaceholderConfigurer()
      pspc.setLocations(BackAppConfig.DEV_PROPERTIES: _*)
      pspc
    }
  }

  @Profile(Array("test"))
  class TestConfig {
    @Bean
    def propertySourcesPlaceholderConfigurer() = {
      val pspc = new PropertySourcesPlaceholderConfigurer()
      pspc.setLocations(BackAppConfig.TEST_PROPERTIES: _*)
      pspc
    }
  }

  @Profile(Array("prod"))
  class ProdConfig {
    @Bean
    def propertySourcesPlaceholderConfigurer() = {
      val pspc = new PropertySourcesPlaceholderConfigurer()
      pspc.setLocations(BackAppConfig.PROD_PROPERTIES: _*)
      pspc
    }
  }

  //  implicit def toRowMapper[T](function2: (ResultSet, Int) => T): RowMapper[T] = {
  //    new RowMapper[T] {
  //      override def mapRow(rs: ResultSet, rowNum: Int): T = function2(rs, rowNum)
  //    }
  //  }
  //
  //  implicit def toDouble(v: String): Double = {
  //    0.0
  //  }

  //  implicit def toRowMapper(function2: (ResultSet, Int) => AnyRef)(): RowMapper[AnyRef] = {
  //    new RowMapper[AnyRef] {
  //      override def mapRow(rs: ResultSet, rowNum: Int): AnyRef = function2(rs, rowNum)
  //    }
  //  }

}


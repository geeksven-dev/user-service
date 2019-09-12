package com.geeksven.userservice.integration

import mu.KotlinLogging
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer

@ContextConfiguration(initializers = [PostgreSQLContainerTest.Initializer::class])
abstract class PostgreSQLContainerTest {

    protected val logger = KotlinLogging.logger {}

    companion object {
        val postgresqlContainer = object : PostgreSQLContainer<Nothing>("postgres:10") {
            init {
                withUsername("postgres")
                withPassword("geeksven4ever")
            }
        }
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            postgresqlContainer.start()

            TestPropertyValues.of("spring.datasource.url=${postgresqlContainer.jdbcUrl}")
                .applyTo(configurableApplicationContext.environment)
        }
    }
}
package com.geeksven.userservice.integration.database

import com.geeksven.userservice.integration.PostgreSQLContainerTest
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc
import org.springframework.boot.test.context.SpringBootTest

@AutoConfigureDataJdbc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
abstract class DatabaseIntegrationTest : PostgreSQLContainerTest()
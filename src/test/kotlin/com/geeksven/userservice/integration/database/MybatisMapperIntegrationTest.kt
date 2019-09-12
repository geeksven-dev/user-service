package com.geeksven.userservice.integration.database

import com.geeksven.userservice.integration.PostgreSQLContainerTest
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
abstract class MybatisMapperIntegrationTest : PostgreSQLContainerTest()
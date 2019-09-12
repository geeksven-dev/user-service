package com.geeksven.userservice.integration.web

import com.geeksven.userservice.integration.PostgreSQLContainerTest
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.core.io.ResourceLoader
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc


@TestConfiguration
internal class CustomizationConfiguration : RestDocsMockMvcConfigurationCustomizer {

    override fun customize(configurer: MockMvcRestDocumentationConfigurer) {
        configurer.operationPreprocessors()
                .withRequestDefaults(prettyPrint())
                .withResponseDefaults(prettyPrint())
    }
}

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [CustomizationConfiguration::class])
@AutoConfigureMockMvc
@AutoConfigureRestDocs
abstract class WebIntegrationTest : PostgreSQLContainerTest() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    protected fun readFileFromClasspath(filename: String)  = resourceLoader.getResource("classpath:$filename").file
    protected fun readTextOfFileFromClasspath(filename: String) = readFileFromClasspath(filename).readText()

    @AfterEach
    private fun cleanDb() {
    }
}
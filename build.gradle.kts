import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.asciidoctor.gradle.AsciidoctorTask

plugins {
    id("com.github.ben-manes.versions") version Versions.GRADLE_VERSIONS_PLUGIN
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.spring") version Versions.KOTLIN
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version Versions.SPRING_DEPENDENCY_MANAGEMENT
    id("org.asciidoctor.convert") version Versions.ASCII_DOCTOR
}

group = "com.geeksven"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:${Versions.MYBATIS}")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }

    implementation("io.github.microutils:kotlin-logging:${Versions.KOTLIN_LOGGING}")

    testImplementation("io.mockk:mockk:${Versions.MOCKK}")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.testcontainers:postgresql:${Versions.TESTCONTAINERS}")
    testImplementation("org.testcontainers:junit-jupiter:${Versions.TESTCONTAINERS}")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:${Versions.MYBATIS}")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctor("org.springframework.restdocs:spring-restdocs-asciidoctor")

    implementation("com.nimbusds:nimbus-jose-jwt:${Versions.NIMBUS_JWT}")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:${Versions.SPRING_OAUTH}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

springBoot {
    buildInfo()
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    from("${tasks.asciidoctor.get().outputDir}/html5") {
        into("static")
    }
}

tasks.withType<AsciidoctorTask> {
    dependsOn(tasks.test)

    attributes["toc"] = ""
    attributes["project-version"] = "1.0.0"
}

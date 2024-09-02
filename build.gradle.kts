plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.flywaydb.flyway") version "10.17.2"
}
apply(plugin = "org.flywaydb.flyway")


group = "dev.amir.golmoradi"
version = "0.0.1-SNAPSHOT"
val jsonWebToken = "0.12.5"
val flywayAndPostgreSQL = "10.17.2"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    /* OAuth */
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    /* JSON WEB TOKEN */
    implementation("io.jsonwebtoken:jjwt-api:${jsonWebToken}")
    implementation("io.jsonwebtoken:jjwt-impl:${jsonWebToken}")
    implementation("io.jsonwebtoken:jjwt-jackson:${jsonWebToken}")
    /* SWAGGER */
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    compileOnly("org.projectlombok:lombok")
    /* FLYWAY MIGRATION */
    implementation("org.flywaydb:flyway-core:${flywayAndPostgreSQL}") // Core Flyway dependency
    implementation("org.flywaydb:flyway-database-postgresql:${flywayAndPostgreSQL}") // postgresql support
    /* Core POSTGRESQL dependency */
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.withType<Test> {
    useJUnitPlatform()
}

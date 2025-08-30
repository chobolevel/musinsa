plugins {
    val kotlinVersion = "1.9.25"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.flywaydb.flyway") version "7.13.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    kotlin("kapt") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "com.musinsa"
version = "0.0.1-SNAPSHOT"
description = "musinsa"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // query dsl
    val queryDslVersion = "5.0.0"
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")

    // envers
    implementation("org.springframework.data:spring-data-envers")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // db-mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    url = "jdbc:mysql://localhost:3306/musinsa"
    user = "root"
    password = "rkddlswo218@"
    baselineVersion = "0"
    outOfOrder = true
}

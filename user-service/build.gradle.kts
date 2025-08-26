plugins {
    id("org.jlleitschuh.gradle.ktlint")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
}

val querydslVersion = "5.0.0"

dependencies {
    implementation(project(":common"))

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // envers
    implementation("org.springframework.data:spring-data-envers")

    // QueryDSL(SQL Injection issue)
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")

    runtimeOnly("com.mysql:mysql-connector-j")
}

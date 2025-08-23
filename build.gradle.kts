/*
* [plugins]
* 루트에서 버전 관리용으로 플러그인을 선언
* apply false 옵션은 루트에서는 실제로 적용하지 않고 하위 모듈에서만 적용
* 멀티 모듈 프로젝트에서 권장 방식(버전 충돌 방지, 하위 모듈별로 필요한 플러그인만 적용 가능
* */
plugins {
    kotlin("jvm") version "1.9.25" // kotlin jvm 프로젝트 설정
    kotlin("plugin.spring") version "1.9.25" apply false // spring 관련 kotlin 플로그인
    kotlin("plugin.jpa") version "1.9.25" apply false // kotlin jpa 플러그인
    id("org.springframework.boot") version "3.5.5" apply false // spring boot 실행/빌드
    id("io.spring.dependency-management") version "1.1.7" apply false // BOM 기반 의존성 관리
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1" apply false
}

/*
* 프로젝트 식별 정보
* */
group = "com"
version = "0.0.1-SNAPSHOT"
description = "musinsa"

/*
* [java]
* JDK 17 사용 설정
* */
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

/*
* [repositories]
* Maven Central에서 의존성 다운로드
* */
repositories {
    mavenCentral()
}

/*
* [subprojects]
* 하위 모듈 공통 설정
* */
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }
}

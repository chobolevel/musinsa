plugins {
    id("org.jlleitschuh.gradle.ktlint")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    // 공통 DTO, Config, Validator 등에서 클래스 메타데이터 활용
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // DTO를 JSON 변환/매핑, API 요청/응답 Service 각 데이터 전달에 활용
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

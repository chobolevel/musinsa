package com.musinsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.musinsa.common.properties")
class MusinsaApplication

fun main(args: Array<String>) {
    runApplication<MusinsaApplication>(*args)
}

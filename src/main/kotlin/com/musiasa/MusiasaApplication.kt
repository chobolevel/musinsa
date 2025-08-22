package com.musiasa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusiasaApplication

fun main(args: Array<String>) {
    runApplication<MusiasaApplication>(*args)
}

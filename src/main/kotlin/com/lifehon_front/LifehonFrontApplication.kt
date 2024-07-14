package com.lifehon_front

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.oshai.kotlinlogging.KotlinLogging

public val logger = KotlinLogging.logger {}
@SpringBootApplication
class LifehonFrontApplication
fun main(args: Array<String>) {

    runApplication<LifehonFrontApplication>(*args)
}

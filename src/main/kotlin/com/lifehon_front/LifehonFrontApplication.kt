package com.lifehon_front

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


public val logger = KotlinLogging.logger {}
@SpringBootApplication
class LifehonFrontApplication
fun main(args: Array<String>) {

    runApplication<LifehonFrontApplication>(*args)
}

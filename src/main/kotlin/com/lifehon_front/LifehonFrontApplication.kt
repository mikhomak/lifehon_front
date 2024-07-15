package com.lifehon_front

import com.lifehon_front.security.LifehonAuthenticationProvider
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider


public val logger = KotlinLogging.logger {}
@SpringBootApplication
class LifehonFrontApplication
fun main(args: Array<String>) {

    runApplication<LifehonFrontApplication>(*args)
}
@Bean
fun authenticationProvider(): AuthenticationProvider {
    return LifehonAuthenticationProvider()
}
package com.lifehon_front

import com.HobbiesQuery
import com.apollographql.apollo3.ApolloClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class LifehonFrontApplication

suspend fun main(args: Array<String>) {
	runApplication<LifehonFrontApplication>(*args)
}

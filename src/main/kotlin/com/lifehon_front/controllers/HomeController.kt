package com.lifehon_front.controllers

import com.HobbiesQuery
import com.lifehon_front.service.ApolloServerConnector
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/")
    suspend fun home(model: Model): String {
        val response = apolloServerConnector.apolloClient.query(HobbiesQuery()).execute();

        model["hobbies"] = response.data?.hobbies
        return "home"
    }
}
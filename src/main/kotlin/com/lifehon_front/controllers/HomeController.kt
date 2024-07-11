package com.lifehon_front.controllers

import com.HobbiesQuery
import com.apollographql.apollo3.ApolloClient
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    suspend fun blog(model: Model): String {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("http://localhost:8600/front-api/v1/")
            .build()

        val response = apolloClient.query(HobbiesQuery()).execute();

        model["hobbies"] = response.data
        model["fun"] = "heh"
        return "blog"
    }
}
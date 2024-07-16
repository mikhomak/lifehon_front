package com.lifehon_front.controllers

import com.lifehon_front.service.ApolloServerConnector
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/login")
    suspend fun login(model: Model): String {

        return "login"
    }


}
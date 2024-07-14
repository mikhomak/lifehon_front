package com.lifehon_front.controllers

import com.lifehon_front.service.ApolloServerConnector
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class LoginController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/login")
    suspend fun login(model: Model): String {

        return "login"
    }

    @PostMapping("/login")
    suspend fun doLogin(userName: String, password: String, model: Model): String {

        return "login"
    }
}
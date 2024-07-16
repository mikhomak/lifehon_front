package com.lifehon_front.controllers

import com.UserForNameQuery
import com.lifehon_front.service.ApolloServerConnector
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class MyAccountController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/account/{name}")
    suspend fun home(@PathVariable(name = "name") name: String, model: Model): String {
        val userResponse = apolloServerConnector.apolloClient.query(UserForNameQuery(name, 0)).execute();
        model["user"] = userResponse.data?.getUserForName;
        return "my-account"
    }

}
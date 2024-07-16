package com.lifehon_front.controllers

import com.HobbiesQuery
import com.UsersQuery
import com.lifehon_front.service.ApolloServerConnector
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/")
    suspend fun home(model: Model): String {
        val hobbiesResponse = apolloServerConnector.apolloClient.query(HobbiesQuery()).execute();
        val usersResponse = apolloServerConnector.apolloClient.query(UsersQuery()).execute();

        model["hobbies"] = hobbiesResponse.data?.hobbies
        model["users"] = usersResponse.data?.users
        return "home"
    }


    @GetMapping("/about")
    suspend fun about(model: Model): String {
        val usersResponse = apolloServerConnector.apolloClient.query(UsersQuery()).execute();

        model["users"] = usersResponse.data?.users
        return "about"
    }


}
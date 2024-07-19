package com.lifehon_front.controllers

import com.AddHobbyToUserMutation
import com.GetUserForNameQuery
import com.lifehon_front.logger
import com.lifehon_front.service.ApolloServerConnector
import com.lifehon_front.service.LifehonUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/account/")
class MyAccountController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/{name}")
    suspend fun home(@PathVariable(name = "name") name: String, request: HttpServletRequest, model: Model): String {
        val userResponse = apolloServerConnector.apolloClient.query(GetUserForNameQuery(name, 0)).execute();
        model["user"] = userResponse.data?.getUserForName;
        model["is_the_same_account"] =
            userResponse.data?.getUserForName?.name?.equals(LifehonUtils.getSessionUser(request));
        return "my-account"
    }

    @PostMapping("/addHobby")
    suspend fun addHobby(
        @RequestParam(name = "hobbyName") hobbyName: String,
        request: HttpServletRequest,
        model: Model
    ): String {
        LifehonUtils.getSessionUser(request)?.name?.let {
            val addHobbyToUserResponse =
                apolloServerConnector.apolloClient.mutation(AddHobbyToUserMutation(it, hobbyName)).execute();
            if (addHobbyToUserResponse.hasErrors()) {
                logger.error {
                    "Error at adding the hobby [$hobbyName] to user [$it] and the error is [${
                        addHobbyToUserResponse.errors?.map { toString() }?.joinToString { "," }
                    }]"
                };
                LifehonUtils.addErrorToModel("Error at adding the hobby", model);
            } else {
                LifehonUtils.addSuccessToModel("Hobby ${hobbyName}!", model);
                logger.info { "Added hobby [$hobbyName] to user [$it]" };
            }
            return "redirect:/account/${it}";
        }
        return "redirect:/";
    }

}
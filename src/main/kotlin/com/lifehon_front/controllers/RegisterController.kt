package com.lifehon_front.controllers

import com.CreateUserMutation
import com.lifehon_front.controllers.forms.RegisterForm
import com.lifehon_front.service.ApolloServerConnector
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.AbstractBindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegisterController(val apolloServerConnector: ApolloServerConnector) {

    @GetMapping("/register")
    suspend fun login(model: Model): String {

        model.addAttribute("registerForm", RegisterForm());
        return "register"
    }

    @PostMapping("/register")
    suspend fun doLogin(
        @ModelAttribute registerForm: RegisterForm,
        bindingResult: AbstractBindingResult,
        model: Model
    ): String {
        val createUserResult = apolloServerConnector.apolloClient.mutation(
            CreateUserMutation(
                registerForm.name,
                registerForm.email,
                registerForm.password,
                registerForm.consent,
                registerForm.publicProfile
            )
        ).execute();

        if (createUserResult.hasErrors()) {

            model.addAttribute("registerForm", registerForm);
            return "register";
        }

        return "home"
    }
}
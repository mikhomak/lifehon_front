package com.lifehon_front.controllers.forms

class RegisterForm(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val consent: Boolean = true,
    val publicProfile: Boolean = true
)


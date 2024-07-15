package com.lifehon_front.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException

class LifehonAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        if (authentication == null) {
            throw UsernameNotFoundException("Cannot login!");
        }
        authentication.name;
        TODO("Not yet implemented")
    }

    override fun supports(authentication: Class<*>?): Boolean {
        TODO("Not yet implemented")
    }
}
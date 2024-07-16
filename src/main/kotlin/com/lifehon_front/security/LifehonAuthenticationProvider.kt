package com.lifehon_front.security

import com.LoginMutation
import com.lifehon_front.service.ApolloServerConnector
import kotlinx.coroutines.runBlocking
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException

class LifehonAuthenticationProvider(private val apolloServerConnector: ApolloServerConnector) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        if (authentication == null) {
            throw UsernameNotFoundException("Cannot login!");
        }
        val gqlLoginResponse = runBlocking {
            apolloServerConnector.apolloClient.mutation(
                LoginMutation(
                    authentication.name,
                    authentication.credentials.toString()
                )
            ).execute()
        }
        if (gqlLoginResponse.hasErrors()) {
            throw UsernameNotFoundException("Cannot login!");
        }
        val userAuthority = SimpleGrantedAuthority("USER");
        val tokenAuth = UsernamePasswordAuthenticationToken(
            authentication.name, authentication.credentials,
            listOf(userAuthority)
        )
        tokenAuth.details = gqlLoginResponse.data?.loginUser;
        return tokenAuth;
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return true;
    }
}
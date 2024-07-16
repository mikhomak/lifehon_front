package com.lifehon_front.filters

import com.CheckTokenQuery
import com.lifehon_front.service.ApolloServerConnector
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.runBlocking
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter


class LifehonGqlAuthFilter(
    private val apolloServerConnector: ApolloServerConnector,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val currentAuthentication = SecurityContextHolder.getContext().authentication;

        if (currentAuthentication != null) {
            val token = currentAuthentication.details;
            if (token != null) {
                val checkTokenResponse = runBlocking {
                    apolloServerConnector.apolloClient.query(CheckTokenQuery(token.toString())).execute();
                }
                if (!checkTokenResponse.hasErrors()) {
                    filterChain.doFilter(request, response)
                    return;
                }
            }
        }

        SecurityContextHolder.getContext().authentication =
            AnonymousAuthenticationToken("ANON", "ANON", listOf(SimpleGrantedAuthority("ANONYMOUS")));
        filterChain.doFilter(request, response)
    }


}
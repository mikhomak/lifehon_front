package com.lifehon_front.security

import com.lifehon_front.filters.LifehonGqlAuthFilter
import com.lifehon_front.service.ApolloServerConnector
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity

class LifehonSecurityConfig(
    private val apolloServerConnector: ApolloServerConnector,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize("/login", anonymous)
                authorize("/about", hasAuthority("USER"))
                authorize("/register", anonymous)
            }
            formLogin {
                loginPage = "/login"
                defaultSuccessUrl("/", true)
            }
            addFilterAt<BasicAuthenticationFilter>(
                LifehonGqlAuthFilter(apolloServerConnector)
            )
            httpBasic { }
        }
        return http.build();
    }

    @Bean
    fun authenticationProvider(apolloServerConnector: ApolloServerConnector): AuthenticationProvider {
        return LifehonAuthenticationProvider(apolloServerConnector);
    }
}
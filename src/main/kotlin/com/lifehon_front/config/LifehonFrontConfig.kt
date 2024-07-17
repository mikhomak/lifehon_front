package com.lifehon_front.config

import com.lifehon_front.filters.LifehonModelInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect


@Configuration
class LifehonFrontConfig : WebMvcConfigurer {
    @Bean
    fun securityDialect(): SpringSecurityDialect {
        return SpringSecurityDialect()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry);
        registry.addInterceptor(LifehonModelInterceptor());
    }
}
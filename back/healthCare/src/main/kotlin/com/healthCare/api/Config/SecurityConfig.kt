@file:Suppress("DEPRECATION")

package com.healthCare.api.Config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.session.web.http.HeaderHttpSessionIdResolver.xAuthToken
import org.springframework.session.web.http.HttpSessionIdResolver


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Bean
    fun httpSessionIdResolver(): HttpSessionIdResolver {
        return xAuthToken()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .cors().and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/user/create")
            .permitAll().and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic()
    }
}

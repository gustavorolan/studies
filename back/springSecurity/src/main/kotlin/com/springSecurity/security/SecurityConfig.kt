package com.springSecurity.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

@EnableWebSecurity
class SecurityConfig(
    @Lazy
    private val jwtAuthFilter: JwtAuthFilter
) {
    companion object {
        val APPLICATION_USERS: List<UserDetails> = listOf(
            User("gustavo", "123456", Collections.singletonList(SimpleGrantedAuthority("ROLE_ADMIN"))), User("fernando", "123456", Collections.singletonList(SimpleGrantedAuthority("ROLE_USER")))
        )
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): DefaultSecurityFilterChain {
        httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .httpBasic()
        return httpSecurity.build()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(userDetailService())
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        return daoAuthenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        return UserDetailsService(@Override
        fun(email: String): UserDetails = APPLICATION_USERS.find { it.username == email }!!)
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager
}
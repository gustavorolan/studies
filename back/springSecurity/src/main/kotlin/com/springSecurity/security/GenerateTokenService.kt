package com.springSecurity.security

import com.springSecurity.controller.dto.LoginRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class GenerateTokenService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {
    fun generate(loginRequest: LoginRequest): String {
        authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password))

        val user = userDetailsService.loadUserByUsername(loginRequest.email)

        return jwtService.generateToken(user)
    }
}
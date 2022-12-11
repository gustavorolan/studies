package com.springSecurity.controller

import com.springSecurity.controller.dto.LoginRequest
import com.springSecurity.security.GenerateTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val generateTokenService: GenerateTokenService
) {
    @GetMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> =
        ResponseEntity.ok(generateTokenService.generate(loginRequest))
}
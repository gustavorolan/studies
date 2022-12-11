package com.springSecurity.controller.dto

data class LoginRequest(
    val email: String,
    val password: String
) {
    constructor() : this("", "")
}

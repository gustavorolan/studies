package com.springSecurity.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GreetingController {
    @GetMapping("/hi")
    fun hi() = ResponseEntity.ok("Hi")
}
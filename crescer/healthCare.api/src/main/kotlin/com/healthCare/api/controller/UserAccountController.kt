package com.healthCare.api.controller

import com.healthCare.api.controller.request.CreateNewUserRequest
import com.healthCare.api.controller.response.UserAccountResponse
import com.healthCare.api.service.user.CreateNewUserService
import com.healthCare.api.service.user.GetUserService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/user")
class UserAccountController (
    private val createNewUserService: CreateNewUserService,
    private val getUserService: GetUserService
    ) {
    @PostMapping("/create")
    fun create(@Valid @RequestBody request:  CreateNewUserRequest) {
        createNewUserService.create(request)
    }

    @GetMapping("/me")
    fun get(): UserAccountResponse? {
        return getUserService.get()
    }
}
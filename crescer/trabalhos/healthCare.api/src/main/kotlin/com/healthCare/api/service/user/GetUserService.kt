package com.healthCare.api.service.user

import com.healthCare.api.controller.response.UserAccountResponse
import com.healthCare.api.mapper.UserMapper
import org.springframework.stereotype.Service


@Service
class GetUserService(
    private val findUserAuthenticatedService: FindUserAuthenticatedService,
    private val userResponseMapper: UserMapper
) {

    fun get(): UserAccountResponse {
        val user = findUserAuthenticatedService.getUser()
        return userResponseMapper.toResponse(user)
    }
}

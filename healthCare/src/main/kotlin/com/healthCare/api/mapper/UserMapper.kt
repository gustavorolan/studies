package com.healthCare.api.mapper

import com.healthCare.api.controller.response.UserAccountResponse
import com.healthCare.api.model.UserAccount
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toResponse(user: UserAccount): UserAccountResponse {
        return UserAccountResponse(
            email = user.email,
            id = user.id)
    }
}
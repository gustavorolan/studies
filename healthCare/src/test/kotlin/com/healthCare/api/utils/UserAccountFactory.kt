package com.healthCare.api.utils

import com.healthCare.api.controller.request.CreateNewUserRequest
import com.healthCare.api.controller.response.UserAccountResponse
import com.healthCare.api.model.UserAccount

class UserAccountFactory {
    companion object {
        fun getUserAccount(): UserAccount =
            UserAccount(
                id = 1,
                email = "user@email.com",
                password = "12345678",
                permissionList = listOf()
            )

        fun getUserAccountRequest(): CreateNewUserRequest =
            CreateNewUserRequest(
                id = 1,
                email = "user@email.com",
                password = "12345678",
            )

        fun getUserAccountTwo(): UserAccount =
            UserAccount(
                id = 2,
                email = "secondUser@email.com",
                password = "12345678",
                permissionList = listOf()
            )

        fun getUserAccountResponse(): UserAccountResponse =
            UserAccountResponse(
                email = "user@email.com",
                id = 1
            )
    }
}
package com.healthCare.api.mapper

import com.healthCare.api.utils.UserAccountFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito

internal class UserMapperTest {

    private val userMapper:UserMapper= UserMapper()

    @Test
    fun `Should map a user entity to response`() {
        val userAccount = UserAccountFactory.getUserAccount()
        val userAccountResponse = UserAccountFactory.getUserAccountResponse()

        val result = userMapper.toResponse(userAccount)

        Assertions.assertEquals(userAccountResponse,result)
    }
}
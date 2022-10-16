package com.healthCare.api.user

import com.healthCare.api.model.UserAccount
import com.healthCare.api.repository.UserAccountRepository
import com.healthCare.api.user.CreateNewUserService
import com.healthCare.api.utils.UserAccountFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.security.crypto.password.PasswordEncoder

internal class CreateNewUserServiceTest {

    private val userAccountRepository= Mockito.mock(UserAccountRepository::class.java)
    private val passwordEncoder = Mockito.mock(PasswordEncoder::class.java)

    private val createNewUserService= CreateNewUserService(
        userAccountRepository,
        passwordEncoder
    )

    private val captor = ArgumentCaptor.forClass(UserAccount::class.java)

    @Test
    fun create() {
        val userAccount = UserAccountFactory.getUserAccount()
        val userAccountRequest = UserAccountFactory.getUserAccountRequest()
        val passwordEncoded = userAccount.password

        Mockito.`when`(passwordEncoder.encode(userAccountRequest.password))
            .thenReturn(passwordEncoded)

        createNewUserService.create(userAccountRequest)

        Mockito.verify(passwordEncoder).encode(userAccountRequest.password)
        Mockito.verify(userAccountRepository).save(captor.capture())

        Assertions.assertEquals(userAccount,captor.value)

        Mockito.verifyNoMoreInteractions(
            userAccountRepository,
            passwordEncoder
        )
    }
}
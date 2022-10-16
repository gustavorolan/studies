package com.healthCare.api.service

import com.healthCare.api.user.FindUserAuthenticatedService
import com.healthCare.api.user.GetUserService
import com.healthCare.api.utils.UserAccountFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

internal class VerifyIfAuthorIsUserLoggedTest {

    private val findUserAuthenticatedService= Mockito.mock(FindUserAuthenticatedService::class.java)
    private val verifyIfAuthorIsUserLogged = VerifyIfAuthorIsUserLogged(findUserAuthenticatedService)

    @Test
    fun `Should run without throwing nothing`() {
        val userAccount = UserAccountFactory.getUserAccount()

        Mockito.`when`(findUserAuthenticatedService.getUser()).thenReturn(userAccount)

        verifyIfAuthorIsUserLogged.verify(userAccount)

        Mockito.verify(findUserAuthenticatedService).getUser()

        Mockito.verifyNoMoreInteractions(findUserAuthenticatedService)
    }

    @Test
    fun `Should throw an exception if author is not logged`() {
        val userAccount = UserAccountFactory.getUserAccount()
        val secondUserAccount = UserAccountFactory.getUserAccountTwo()

        Mockito.`when`(findUserAuthenticatedService.getUser()).thenReturn(userAccount)


        val exception: ResponseStatusException = Assertions.assertThrows(ResponseStatusException::class.java) {
            verifyIfAuthorIsUserLogged.verify(secondUserAccount)
        }


        Mockito.verify(findUserAuthenticatedService).getUser()

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.status)
        Assertions.assertEquals("You're not the author", exception.reason)

        Mockito.verifyNoMoreInteractions(findUserAuthenticatedService)
    }
}
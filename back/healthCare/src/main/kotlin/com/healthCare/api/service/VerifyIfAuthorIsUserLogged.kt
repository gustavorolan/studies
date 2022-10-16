package com.healthCare.api.service

import com.healthCare.api.model.Contract
import com.healthCare.api.model.UserAccount
import com.healthCare.api.user.FindUserAuthenticatedService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VerifyIfAuthorIsUserLogged (
    private val findUserAuthenticatedService: FindUserAuthenticatedService
        ) {
    fun verify(author: UserAccount){
        val userLogged = findUserAuthenticatedService.getUser()
        if (author != userLogged)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,"You're not the author")
    }
}
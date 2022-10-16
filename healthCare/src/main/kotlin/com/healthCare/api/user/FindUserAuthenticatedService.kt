package com.healthCare.api.user

import com.healthCare.api.model.UserAccount
import com.healthCare.api.repository.UserAccountRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class FindUserAuthenticatedService(
    private val userAccountRepository: UserAccountRepository
    ) {

    fun getUser(): UserAccount {
        val authentication = SecurityContextHolder.getContext().authentication
        val securityUser = authentication.principal as SecurityUser
        return userAccountRepository.findById(securityUser.id).get()
    }
}


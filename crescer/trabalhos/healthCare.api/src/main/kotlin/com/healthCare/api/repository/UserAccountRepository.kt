package com.healthCare.api.repository

import com.healthCare.api.model.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface UserAccountRepository: JpaRepository<UserAccount, Long> {
    fun findByEmail(email:String): UserAccount
}
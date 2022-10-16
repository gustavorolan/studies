package com.healthCare.api.user

import com.healthCare.api.repository.UserAccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class GetUserSecurityService (
    private val userAccountRepository: UserAccountRepository
    ) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String?): UserDetails? {
        val userAccount = userAccountRepository.findByEmail(email!!)
        return SecurityUser(userAccount)
    }
}
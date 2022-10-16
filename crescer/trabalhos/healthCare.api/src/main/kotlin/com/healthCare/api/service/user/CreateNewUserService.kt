package com.healthCare.api.service.user

import com.healthCare.api.controller.request.CreateNewUserRequest
import com.healthCare.api.model.UserAccount
import com.healthCare.api.repository.PermissionRepository
import com.healthCare.api.repository.UserAccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.validation.constraints.Null


@Service
class CreateNewUserService (
    private val userAccountRepository: UserAccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val permissionRepository: PermissionRepository
    ){
  
    fun create(request: CreateNewUserRequest) {
        val passwordEncoded = passwordEncoder.encode(request.password)

        val userAccount = UserAccount(request.id,request.email,passwordEncoded, arrayListOf())
        userAccountRepository.save(userAccount)
    }

}

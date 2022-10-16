package com.healthCare.api.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Null

data class CreateNewUserRequest(

    @Null
    val id:Long,

    @Email
    val email:String,

    @NotEmpty
    val password:String

)

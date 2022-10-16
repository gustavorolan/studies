package com.healthCare.api.service

import com.healthCare.api.model.Contract
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VerifyIfPlanStillBeingUsed {
    fun verify(contractList: List<Contract>){
        if (contractList.isNotEmpty())
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,"There's a contract using this plan")
    }
}
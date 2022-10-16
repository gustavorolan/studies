package com.healthCare.api.service

import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.mapper.HealthCarePlanMapper
import com.healthCare.api.repository.ContractRepository
import com.healthCare.api.repository.HealthCarePlanRepository
import com.healthCare.api.service.finder.HealthCarePlanFinder
import org.springframework.stereotype.Service

@Service
class RemoveHealthCarePlanByIdService (
    private val contractRepository: ContractRepository,
    private val healthCarePlanFinder: HealthCarePlanFinder,
    private val verifyIfPlanStillBeingUsed: VerifyIfPlanStillBeingUsed,
    private val healthCarePlanMapper: HealthCarePlanMapper,
    private val healthCarePlanRepository: HealthCarePlanRepository,
    private val verifyIfAuthorIsUserLogged: VerifyIfAuthorIsUserLogged
        ){
    fun delete(id: Long): HealthCarePlanResponse {
        val healthCarePlan = healthCarePlanFinder.findByid(id)
        val findContractsByHealthCarePlan = contractRepository.findAllByHealthCarePlan(healthCarePlan)
        verifyIfPlanStillBeingUsed.verify(findContractsByHealthCarePlan)
        verifyIfAuthorIsUserLogged.verify(healthCarePlan.author)
        healthCarePlanRepository.delete(healthCarePlan)
        return healthCarePlanMapper.toResponse(healthCarePlan)
    }

}

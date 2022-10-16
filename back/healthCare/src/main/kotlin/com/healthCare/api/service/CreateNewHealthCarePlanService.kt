package com.healthCare.api.service

import com.healthCare.api.controller.request.CreateNewHealthCarePlanRequest
import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.mapper.HealthCarePlanMapper
import com.healthCare.api.repository.HealthCarePlanRepository
import com.healthCare.api.user.FindUserAuthenticatedService
import com.healthCare.api.validator.FloorPriceValidator
import org.springframework.stereotype.Service

@Service
class CreateNewHealthCarePlanService(
    private  val healthCarePlanRepository: HealthCarePlanRepository,
    private val healthCarePlanMapper: HealthCarePlanMapper,
    private  val floorPriceValidator: FloorPriceValidator,
    private val findUserAuthenticatedService: FindUserAuthenticatedService
    ){
    fun post(request: CreateNewHealthCarePlanRequest): HealthCarePlanResponse {
        val userAuthenticated = findUserAuthenticatedService.getUser()
        floorPriceValidator.verify(request.floorPrice)
        val entity = healthCarePlanMapper.toEntity(request,userAuthenticated)
        healthCarePlanRepository.save(entity)
        return healthCarePlanMapper.toResponse(entity)
    }
}

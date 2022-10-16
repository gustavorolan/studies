package com.healthCare.api.service

import com.healthCare.api.controller.request.CreateNewHealthCarePlanRequest
import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.mapper.HealthCarePlanMapper
import com.healthCare.api.repository.HealthCarePlanRepository
import com.healthCare.api.validator.FloorPriceValidator
import org.springframework.stereotype.Service

@Service
class CreateNewHealthCarePlanService (
    private  val healthCarePlanRepository: HealthCarePlanRepository,
    private val healthCarePlanMapper: HealthCarePlanMapper,
    private  val floorPriceValidator: FloorPriceValidator
    ){

    fun post(request: CreateNewHealthCarePlanRequest): HealthCarePlanResponse {
        floorPriceValidator.verify(request.floorPrice)
        val entity = healthCarePlanMapper.toEntity(request)
        healthCarePlanRepository.save(entity)
        return healthCarePlanMapper.toResponse(entity)
    }

}

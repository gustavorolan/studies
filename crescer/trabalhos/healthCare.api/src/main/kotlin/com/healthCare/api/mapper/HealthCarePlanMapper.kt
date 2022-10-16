package com.healthCare.api.mapper

import com.healthCare.api.controller.request.CreateNewHealthCarePlanRequest
import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.model.HealthCarePlan
import org.springframework.stereotype.Component

@Component
class HealthCarePlanMapper {
    fun toEntity(request: CreateNewHealthCarePlanRequest): HealthCarePlan = HealthCarePlan(request.id,request.coverage,request.accommodation,request.obstetrics,request.airlift,request.floorPrice)

    fun toResponse(entity: HealthCarePlan): HealthCarePlanResponse = HealthCarePlanResponse(entity.id )
}
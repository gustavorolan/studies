package com.healthCare.api.mapper

import com.healthCare.api.controller.request.CreateNewHealthCarePlanRequest
import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.model.HealthCarePlan
import com.healthCare.api.model.UserAccount
import org.springframework.stereotype.Component

@Component
class HealthCarePlanMapper(
    private val userMapper: UserMapper
    ) {
    fun toEntity(request: CreateNewHealthCarePlanRequest,author: UserAccount): HealthCarePlan =
        HealthCarePlan(
           id= request.id,
           coverage= request.coverage,
           accommodation = request.accommodation,
           obstetrics = request.obstetrics,
           airlift = request.airlift,
           floorPrice = request.floorPrice,
           author = author)

    fun toResponse(entity: HealthCarePlan): HealthCarePlanResponse = HealthCarePlanResponse(
        id=entity.id,
        userAccountResponse =  userMapper.toResponse(entity.author)
    )
}
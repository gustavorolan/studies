package com.healthCare.api.utils

import com.healthCare.api.controller.request.CreateNewHealthCarePlanRequest
import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.model.HealthCarePlan
import com.healthCare.api.model.HealthCarePlanAccommodation
import com.healthCare.api.model.HealthCarePlanCoverage
import java.math.BigDecimal

class HealthCarePlanFactory (){
    companion object{
        fun getHealthCarePlan(): HealthCarePlan =
            HealthCarePlan(
                id=1,
                coverage = HealthCarePlanCoverage.NATIONAL,
                accommodation = HealthCarePlanAccommodation.PRIVATE,
                obstetrics = true,
                airlift = true,
                floorPrice = BigDecimal(100),
                author = UserAccountFactory.getUserAccount()
            )

        fun getHealthCarePlanResponse():HealthCarePlanResponse =
            HealthCarePlanResponse(
                id = 1,
                userAccountResponse = UserAccountFactory.getUserAccountResponse()
            )

        fun getHealthCarePlanRequest(): CreateNewHealthCarePlanRequest =
            CreateNewHealthCarePlanRequest(
                id = 1,
                coverage = HealthCarePlanCoverage.NATIONAL,
                accommodation = HealthCarePlanAccommodation.PRIVATE,
                obstetrics = true,
                airlift = true,
                floorPrice = BigDecimal(100),
            )
    }

}
package com.healthCare.api.controller

import com.healthCare.api.controller.request.CreateNewHealthCarePlanRequest
import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.service.CreateNewHealthCarePlanService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/healthCarePlan")
class CreateNewHealthCarePlanController(
    private val createNewHealthCarePlanService: CreateNewHealthCarePlanService
    ) {

    @PostMapping
    fun post(@Valid @RequestBody request: CreateNewHealthCarePlanRequest): HealthCarePlanResponse {
        return createNewHealthCarePlanService.post(request);
    }
}
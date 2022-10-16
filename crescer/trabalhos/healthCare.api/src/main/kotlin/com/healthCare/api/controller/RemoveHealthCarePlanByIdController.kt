package com.healthCare.api.controller

import com.healthCare.api.controller.response.HealthCarePlanResponse
import com.healthCare.api.service.RemoveHealthCarePlanByIdService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/healthCarePlan")
class RemoveHealthCarePlanByIdController(
    private val removeHealthCarePlanByIdService: RemoveHealthCarePlanByIdService
    ) {

    @DeleteMapping("/{id}")
    fun post(@PathVariable id:Long) : HealthCarePlanResponse {
        return removeHealthCarePlanByIdService.delete(id);
    }
}
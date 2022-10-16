package com.healthCare.api.service.finder

import com.healthCare.api.model.HealthCarePlan
import com.healthCare.api.repository.HealthCarePlanRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class HealthCarePlanFinder(
    private val healthCarePlanRepository: HealthCarePlanRepository
) {
    fun findByid(id: Long): HealthCarePlan = healthCarePlanRepository
        .findByid(id)
        .orElseThrow()
}

private fun <T> Optional<T>.orElseThrow(responseStatusException: ResponseStatusException): ResponseStatusException {
    return ResponseStatusException(HttpStatus.NOT_FOUND, "Health care plan doesn't exist")
}



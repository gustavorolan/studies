package com.healthCare.api.repository

import com.healthCare.api.model.HealthCarePlan
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface HealthCarePlanRepository : JpaRepository<HealthCarePlan, Long>{
    fun findByid(id:Long):Optional<HealthCarePlan>
}
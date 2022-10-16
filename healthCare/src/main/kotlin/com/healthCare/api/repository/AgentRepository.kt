package com.healthCare.api.repository

import com.healthCare.api.model.HealthCarePlan
import org.springframework.data.jpa.repository.JpaRepository

interface AgentRepository : JpaRepository<HealthCarePlan, Long>{

}
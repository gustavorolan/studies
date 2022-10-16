package com.healthCare.api.repository

import com.healthCare.api.model.Contract
import com.healthCare.api.model.HealthCarePlan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ContractRepository : JpaRepository<Contract, Long>{

     fun findAllByHealthCarePlan(healthCarePlan: HealthCarePlan):List<Contract>
}
package com.healthCare.api.utils

import com.healthCare.api.model.Contract

class ContractFactory {
    companion object{
        fun getContract()=Contract(
            id=1,
            healthCarePlan = HealthCarePlanFactory.getHealthCarePlan())
    }
}
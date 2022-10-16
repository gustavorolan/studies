package com.healthCare.api.controller.request

import com.healthCare.api.model.HealthCarePlanAccommodation
import com.healthCare.api.model.HealthCarePlanCoverage
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

data class CreateNewHealthCarePlanRequest(
    @Null
    val id:Long,

    @NotEmpty
    val coverage: HealthCarePlanCoverage,

    @NotNull
    val obstetrics:Boolean,

    @NotNull
    val airlift:Boolean,

    @NotNull
    val floorPrice: BigDecimal,

    @NotEmpty
    val accommodation: HealthCarePlanAccommodation,
)

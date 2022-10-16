package com.healthCare.api.service

import com.healthCare.api.mapper.HealthCarePlanMapper
import com.healthCare.api.model.HealthCarePlan
import com.healthCare.api.repository.HealthCarePlanRepository
import com.healthCare.api.user.FindUserAuthenticatedService
import com.healthCare.api.utils.HealthCarePlanFactory
import com.healthCare.api.utils.UserAccountFactory
import com.healthCare.api.validator.FloorPriceValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito


internal class CreateNewHealthCarePlanServiceTest {

    private  val healthCarePlanRepository= Mockito
        .mock(HealthCarePlanRepository::class.java)
    private val healthCarePlanMapper= Mockito
        .mock(HealthCarePlanMapper::class.java)
    private  val floorPriceValidator=Mockito
        .mock(FloorPriceValidator::class.java)
    private val findUserAuthenticatedService=Mockito
        .mock(FindUserAuthenticatedService::class.java)

    private val captor = ArgumentCaptor.forClass(HealthCarePlan::class.java)

    private val createNewHealthCarePlanService = CreateNewHealthCarePlanService(
        healthCarePlanRepository,
        healthCarePlanMapper,
        floorPriceValidator,
        findUserAuthenticatedService
    )

    @Test
    fun `Should create a new health care plan`() {
        val userAccount = UserAccountFactory.getUserAccount()
        val healthCarePlan = HealthCarePlanFactory.getHealthCarePlan()
        val healthCarePlanRequest = HealthCarePlanFactory.getHealthCarePlanRequest()

        Mockito.`when`(findUserAuthenticatedService.getUser()).thenReturn(userAccount)
        Mockito.`when`(healthCarePlanMapper.toEntity(healthCarePlanRequest,userAccount)).thenReturn(healthCarePlan)

        createNewHealthCarePlanService.post(healthCarePlanRequest)

        Mockito.verify(findUserAuthenticatedService).getUser()
        Mockito.verify(healthCarePlanMapper).toEntity(healthCarePlanRequest,userAccount)
        Mockito.verify(floorPriceValidator).verify(healthCarePlanRequest.floorPrice)
        Mockito.verify(healthCarePlanRepository).save(captor.capture())
        Mockito.verify(healthCarePlanMapper).toResponse(healthCarePlan)

        Assertions.assertEquals(healthCarePlan,captor.value)

        Mockito.verifyNoMoreInteractions(
            healthCarePlanRepository,
            healthCarePlanMapper,
            floorPriceValidator,
            findUserAuthenticatedService
        )
    }
}
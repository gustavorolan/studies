package com.healthCare.api.mapper

import com.healthCare.api.utils.HealthCarePlanFactory
import com.healthCare.api.utils.UserAccountFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

internal class HealthCarePlanMapperTest() {

    private val userMapper = Mockito.mock(UserMapper::class.java)
    private val healthCarePlanMapper=HealthCarePlanMapper(userMapper)

    @Test
    fun `Should map healthCare request to entity`() {
        val healthCarePlanRequest = HealthCarePlanFactory.getHealthCarePlanRequest()
        val healthCarePlan = HealthCarePlanFactory.getHealthCarePlan()

        val userAccount = UserAccountFactory.getUserAccount()

        val result = healthCarePlanMapper.toEntity(healthCarePlanRequest,userAccount)

        Assertions.assertEquals(healthCarePlan,result)
    }

    @Test
    fun `Should map healthCare entity to response`() {
        val userAccountResponse = UserAccountFactory.getUserAccountResponse()

        val healthCarePlan = HealthCarePlanFactory.getHealthCarePlan()
        val healthCarePlanResponse = HealthCarePlanFactory.getHealthCarePlanResponse()

        Mockito.`when`(userMapper.toResponse(healthCarePlan.author)).thenReturn(userAccountResponse)

        val result = healthCarePlanMapper.toResponse(healthCarePlan)

        Mockito.verify(userMapper).toResponse(healthCarePlan.author)

        Assertions.assertEquals(healthCarePlanResponse,result)

        Mockito.verifyNoMoreInteractions(userMapper)
    }
}
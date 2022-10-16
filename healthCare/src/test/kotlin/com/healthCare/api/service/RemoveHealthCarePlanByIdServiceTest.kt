package com.healthCare.api.service

import com.healthCare.api.mapper.HealthCarePlanMapper
import com.healthCare.api.model.Contract
import com.healthCare.api.model.HealthCarePlan
import com.healthCare.api.repository.ContractRepository
import com.healthCare.api.repository.HealthCarePlanRepository
import com.healthCare.api.service.finder.HealthCarePlanFinder
import com.healthCare.api.utils.HealthCarePlanFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


import org.mockito.ArgumentCaptor
import org.mockito.Mockito

internal class RemoveHealthCarePlanByIdServiceTest {

    private val contractRepository= Mockito.mock(ContractRepository::class.java)
    private val healthCarePlanFinder= Mockito.mock(HealthCarePlanFinder::class.java)
    private val verifyIfPlanStillBeingUsed= Mockito.mock(VerifyIfPlanStillBeingUsed::class.java)
    private val healthCarePlanMapper= Mockito.mock(HealthCarePlanMapper::class.java)
    private val healthCarePlanRepository= Mockito.mock(HealthCarePlanRepository::class.java)
    private val verifyIfAuthorIsUserLogged=Mockito.mock(VerifyIfAuthorIsUserLogged::class.java)

    private val removeHealthCarePlanByIdService=RemoveHealthCarePlanByIdService(
        contractRepository,
        healthCarePlanFinder,
        verifyIfPlanStillBeingUsed,
        healthCarePlanMapper,
        healthCarePlanRepository,
        verifyIfAuthorIsUserLogged
    )

    private val captor = ArgumentCaptor.forClass(HealthCarePlan::class.java)

    @Test
    fun `Should remove a health care plan`() {
        val healthCarePlan = HealthCarePlanFactory.getHealthCarePlan()
        val emptyContractList = listOf<Contract>();

        Mockito.`when`(healthCarePlanFinder.findByid(healthCarePlan.id))
            .thenReturn(healthCarePlan)
        Mockito.`when`(contractRepository.findAllByHealthCarePlan(healthCarePlan))
            .thenReturn(emptyContractList)

        removeHealthCarePlanByIdService.delete(healthCarePlan.id)

        Mockito.verify(healthCarePlanFinder).findByid(healthCarePlan.id)
        Mockito.verify(contractRepository).findAllByHealthCarePlan(healthCarePlan)
        Mockito.verify(verifyIfPlanStillBeingUsed).verify(emptyContractList)
        Mockito.verify(healthCarePlanMapper).toResponse(healthCarePlan)
        Mockito.verify(verifyIfAuthorIsUserLogged).verify(healthCarePlan.author)
        Mockito.verify(healthCarePlanRepository).delete(captor.capture())

        Assertions.assertEquals(healthCarePlan,captor.value)

        Mockito.verifyNoMoreInteractions(
            contractRepository,
            healthCarePlanFinder,
            verifyIfPlanStillBeingUsed,
            healthCarePlanMapper,
            healthCarePlanRepository,
            verifyIfAuthorIsUserLogged
        )
    }
}
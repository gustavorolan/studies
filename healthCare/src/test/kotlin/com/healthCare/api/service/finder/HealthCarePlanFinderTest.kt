package com.healthCare.api.service.finder

import com.healthCare.api.repository.HealthCarePlanRepository
import com.healthCare.api.utils.HealthCarePlanFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

internal class HealthCarePlanFinderTest {

    private val healthCarePlanRepository = Mockito.mock(HealthCarePlanRepository::class.java)

    private val healthCarePlanFinder = HealthCarePlanFinder(healthCarePlanRepository)

    @Test
    fun `Should run without throwing nothing`() {
        val healthCarePlan = HealthCarePlanFactory.getHealthCarePlan()

        Mockito.`when`(healthCarePlanRepository.findByid(healthCarePlan.id))
            .thenReturn(Optional.of(healthCarePlan))

        healthCarePlanFinder.findByid(healthCarePlan.id)

        Mockito.verify(healthCarePlanRepository).findByid(healthCarePlan.id)

        Mockito.verifyNoMoreInteractions(healthCarePlanRepository)
    }

   @Test
    fun `Should do throw a response status server`() {
        val id:Long = 1

        Mockito.`when`(healthCarePlanRepository.findByid(id))
            .thenReturn(Optional.empty())


        val exception: ResponseStatusException = Assertions.assertThrows(ResponseStatusException::class.java) {
            healthCarePlanFinder.findByid(id)
        }

        Mockito.verify(healthCarePlanRepository).findByid(id)

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.status)
        Assertions.assertEquals("Health care plan not found", exception.reason)

       Mockito.verifyNoMoreInteractions(healthCarePlanRepository)
    }
}







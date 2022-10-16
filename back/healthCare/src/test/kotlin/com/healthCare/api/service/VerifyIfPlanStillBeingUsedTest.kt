package com.healthCare.api.service

import com.healthCare.api.utils.ContractFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

internal class VerifyIfPlanStillBeingUsedTest {

    private val verifyIfPlanStillBeingUsed = VerifyIfPlanStillBeingUsed()

    @Test
    fun `Should Throw an exception if plan still in use`() {
        val contract = ContractFactory.getContract()
        val exception: ResponseStatusException = Assertions.assertThrows(ResponseStatusException::class.java) {
            verifyIfPlanStillBeingUsed.verify(listOf(contract))
        }

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.status)
        Assertions.assertEquals("There's a contract using this plan", exception.reason)

    }
}
package com.healthCare.api.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

internal class FloorPriceValidatorTest {

    private val floorPriceValidator=FloorPriceValidator()

    @Test
    fun verify() {
        val requestBasePrice = BigDecimal.valueOf(90)

        val exception: ResponseStatusException = Assertions.assertThrows(ResponseStatusException::class.java) {
            floorPriceValidator.verify(requestBasePrice)
        }

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.status)
        Assertions.assertEquals("Value is lower than one hundred", exception.reason)
    }
}
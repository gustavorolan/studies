package com.healthCare.api.validator

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@Component
class FloorPriceValidator {

    val LOWEST_BASE_PRICE = BigDecimal.valueOf(100)

    fun verify(requestFloorPrice:BigDecimal){
        if (requestFloorPrice<LOWEST_BASE_PRICE)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Value is lower than one hundread")
    }
}
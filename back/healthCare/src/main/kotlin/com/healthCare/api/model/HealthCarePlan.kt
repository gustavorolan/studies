package com.healthCare.api.model

import java.math.BigDecimal
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY


@Entity
data class HealthCarePlan(

    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id:Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var coverage:HealthCarePlanCoverage,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var accommodation: HealthCarePlanAccommodation,

    @Column(nullable = false)
    var obstetrics:Boolean,

    @Column(nullable = false)
    var airlift:Boolean,

    @Column(nullable = false)
    var floorPrice: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "authorId")
    val author: UserAccount

    )


package com.healthCare.api.model

import javax.persistence.*

@Entity
data class Contract(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? =null,

    @ManyToOne
    @JoinColumn(name="healthCareId")
    val healthCarePlan: HealthCarePlan
)

package com.healthCare.api.model

import javax.persistence.*

@Entity
data class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    @Column(nullable = false)
    var password:String,

    @Column(nullable = false)
    var email:String,

    @OneToMany(fetch = FetchType.EAGER )
    @JoinColumn(name = "permission")
    var permissionList:List<Permission>
)

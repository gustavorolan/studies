package com.healthCare.api.model

import javax.persistence.*

@Entity
data class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    @Column(nullable = false)
    var email:String,

    @Column(nullable = false)
    var password:String,

    @OneToMany(fetch = FetchType.EAGER )
    @JoinColumn(name = "permission")
    var permissionList:List<Permission>
)

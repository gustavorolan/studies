package com.healthCare.api.repository

import com.healthCare.api.model.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : JpaRepository<Permission, Long>{

}
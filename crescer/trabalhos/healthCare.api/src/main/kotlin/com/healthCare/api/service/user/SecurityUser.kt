package com.healthCare.api.service.user

import com.healthCare.api.model.Permission
import com.healthCare.api.model.UserAccount
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class SecurityUser(user: UserAccount) : UserDetails {
    val id: Long
    private val password: String
    private val email: String
    private val permissions: List<SimpleGrantedAuthority>

    init {
        id = user.id
        email = user.email
        password = user.password
        permissions = user.permissionList
            .stream()
            .map { (_, permissionName): Permission ->
                SimpleGrantedAuthority(
                    "ROLE_$permissionName"
                )
            }
            .collect(Collectors.toList())
    }

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return permissions
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
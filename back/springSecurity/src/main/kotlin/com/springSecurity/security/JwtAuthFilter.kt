package com.springSecurity.security

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(AUTHORIZATION)

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken: String = authHeader.substring(7);
        val userEmail: String = jwtService.extractUsername(jwtToken)

        if (SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(userEmail)
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, userDetails.authorities
                )

                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
            filterChain.doFilter(request, response)
        }
    }
}
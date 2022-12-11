package com.springSecurity.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class JwtService {

    @Value("jwtSignKey")
    private var jwtSignKey: String? = "secret"

    fun create(claims: Map<String, Any>, userDetails: UserDetails) = Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.username)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
        .signWith(
            SignatureAlgorithm.HS256,
            jwtSignKey
        )
        .compact()!!;


    fun extractUsername(token: String): String {
        return extractClaim(token, Claims.SUBJECT)
    }

    fun generateToken(userDetails: UserDetails) = create(hashMapOf(), userDetails)

    fun extractExpiration(token: String): String {
        return extractClaim(token, Claims.EXPIRATION)
    }

    fun hasClaim(token: String, claimName: String): Boolean {
        val claims = extractAllClaims(token)
        return claims[claimName] != null;
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val dateString = extractClaim(token, Claims.EXPIRATION)
        val date = Date(dateString.toLong())
        return date.before(Date())
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(jwtSignKey).parseClaimsJws(token).body
    }

    private fun extractClaim(token: String, string: String): String {
        val claims = extractAllClaims(token)
        return claims[string]!!.toString()
    }
}



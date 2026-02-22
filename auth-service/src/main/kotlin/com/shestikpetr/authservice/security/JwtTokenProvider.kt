package com.shestikpetr.authservice.security

import com.shestikpetr.authservice.entities.Credentials
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expiration: Long
) {
    fun generateToken(credentials: Credentials): String =
        Jwts.builder()
            .subject(credentials.email)
            .claim("role", credentials.role.name)
            .claim("userId", credentials.userId.toString())
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey())
            .compact()


    fun validateToken(token: String): Boolean =
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }


    fun getEmailFromToken(token: String): String =
        Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload
            .subject


    private fun getSigningKey(): SecretKey =
        Keys.hmacShaKeyFor(secret.toByteArray())
}
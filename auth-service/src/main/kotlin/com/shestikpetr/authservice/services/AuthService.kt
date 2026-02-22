package com.shestikpetr.authservice.services

import com.shestikpetr.authservice.dto.AuthResponse
import com.shestikpetr.authservice.dto.LoginRequest
import com.shestikpetr.authservice.dto.RegisterRequest
import com.shestikpetr.authservice.entities.Credentials
import com.shestikpetr.authservice.repositories.CredentialsRepository
import com.shestikpetr.authservice.security.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthService(
    private val credentialsRepository: CredentialsRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(request: RegisterRequest): AuthResponse {
        if (credentialsRepository.findByEmail(request.email) != null) {
            throw IllegalArgumentException("Email already registered")
        }

        val credentials = Credentials(
            userId = UUID.randomUUID(),
            email = request.email,
            passwordHash = passwordEncoder.encode(request.password)!!
        )
        credentialsRepository.save(credentials)

        return AuthResponse(
            token = jwtTokenProvider.generateToken(credentials),
            role = credentials.role.name
        )
    }

    fun login(request: LoginRequest): AuthResponse {
        val credentials = credentialsRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("Invalid email or password")

        if (!passwordEncoder.matches(
                request.password,
                credentials.passwordHash
            )
        ) throw IllegalArgumentException("Invalid email or password")

        return AuthResponse(
            token = jwtTokenProvider.generateToken(credentials),
            role = credentials.role.name
        )
    }
}
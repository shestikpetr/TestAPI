package com.shestikpetr.authservice.dto

data class AuthResponse(
    val token: String,
    val role: String
)

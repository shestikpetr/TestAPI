package com.shestikpetr.userservice.dto

import com.shestikpetr.userservice.entities.User
import com.shestikpetr.userservice.enums.SystemRole
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(
    @NotBlank
    val username: String,
    @NotBlank
    @Email
    val email: String,
    @NotBlank
    val password: String
)

data class UpdateUserRequest(
    val username: String?,
    val email: String?,
    val password: String?,
    val avatarUrl: String?
)

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val systemRole: SystemRole,
    val avatarUrl: String?
)

fun User.toResponse() = UserResponse(
    id = id!!,
    username = username,
    email = email,
    systemRole = systemRole,
    avatarUrl = avatarUrl
)

fun CreateUserRequest.toEntity() = User(
    username = username,
    email = email,
    password = password
)
package com.shestikpetr.userservice.dto

data class CreateGroupRequest(
    val name: String,
    val description: String?
)

data class UpdateGroupRequest(
    val name: String?,
    val description: String?,
    val coverUrl: String?
)

data class GroupResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val coverUrl: String?
)
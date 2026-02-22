package com.shestikpetr.userservice.dto

import com.shestikpetr.userservice.entities.Group

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

fun Group.toResponse() = GroupResponse(
    id = id!!,
    name = name,
    description = description,
    coverUrl = coverUrl
)

fun CreateGroupRequest.toEntity() = Group(
    name = name,
    description = description
)
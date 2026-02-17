package com.shestikpetr.userservice.dto

import com.shestikpetr.userservice.enums.GroupRole

data class AddMemberRequest(
    val userId: Long,
    val role: GroupRole
)

data class GroupMembershipResponse(
    val userId: Long,
    val username: String,
    val role: GroupRole
)
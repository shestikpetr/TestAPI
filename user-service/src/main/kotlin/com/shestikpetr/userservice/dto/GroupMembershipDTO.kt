package com.shestikpetr.userservice.dto

import com.shestikpetr.userservice.entities.GroupMembership
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

data class UpdateRoleRequest(
    val role: GroupRole
)

fun GroupMembership.toResponse() = GroupMembershipResponse(
    userId = user.id!!,
    username = user.username,
    role = role
)

data class UserGroupResponse(
    val groupId: Long,
    val groupName: String,
    val role: GroupRole
)

fun GroupMembership.toUserGroupResponse() = UserGroupResponse(
    groupId = group.id!!,
    groupName = group.name,
    role = role
)
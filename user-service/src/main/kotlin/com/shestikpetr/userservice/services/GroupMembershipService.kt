package com.shestikpetr.userservice.services

import com.shestikpetr.userservice.entities.GroupMembership
import com.shestikpetr.userservice.enums.GroupRole
import com.shestikpetr.userservice.repositories.GroupMembershipRepository
import com.shestikpetr.userservice.repositories.UserRepository
import com.shestikpetr.userservice.repositories.GroupRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GroupMembershipService(
    private val groupMembershipRepository: GroupMembershipRepository,
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository
) {
    fun addMember(userId: Long, groupId: Long, role: GroupRole) {
        val user = userRepository.findById(userId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $userId not found"
            )
        }

        val group = groupRepository.findById(groupId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Group with id $groupId not found"
            )
        }

        groupMembershipRepository.findByUserIdAndGroupId(userId, groupId)?.let {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "User with id $userId already a member of group with id $groupId"
            )
        }

        val membership = GroupMembership(user, group, role)
        groupMembershipRepository.save(membership)
    }

    fun removeMember(groupId: Long, userId: Long) {
        val membership =
            groupMembershipRepository.findByUserIdAndGroupId(userId, groupId) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "User with id $userId not found in group with id $groupId"
            )
        groupMembershipRepository.delete(membership)
    }

    fun getGroupMembers(groupId: Long): List<GroupMembership> = groupMembershipRepository.findByGroupId(groupId)

    fun getUserGroups(userId: Long): List<GroupMembership> = groupMembershipRepository.findByUserId(userId)

    fun updateRole(groupId: Long, userId: Long, role: GroupRole) {
        val membership =
            groupMembershipRepository.findByUserIdAndGroupId(userId, groupId) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $userId not found in group with id $groupId"
            )

        membership.role = role
        groupMembershipRepository.save(membership)
    }
}
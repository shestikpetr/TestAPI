package com.shestikpetr.userservice.repositories

import com.shestikpetr.userservice.entities.GroupMembership
import org.springframework.data.jpa.repository.JpaRepository

interface GroupMembershipRepository : JpaRepository<GroupMembership, Long> {
    fun findByGroupId(groupId: Long): List<GroupMembership>
    fun findByUserId(userId: Long): List<GroupMembership>
    fun findByUserIdAndGroupId(userId: Long, groupId: Long): GroupMembership?
}
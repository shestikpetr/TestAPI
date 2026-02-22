package com.shestikpetr.userservice.repositories

import com.shestikpetr.userservice.entities.GroupMembership
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GroupMembershipRepository : JpaRepository<GroupMembership, Long> {
    fun findByGroupId(groupId: Long): List<GroupMembership>
    fun findByUserId(userId: Long): List<GroupMembership>
    fun findByUserIdAndGroupId(userId: Long, groupId: Long): GroupMembership?

    @Query("SELECT gm FROM GroupMembership gm JOIN FETCH gm.user WHERE gm.group.id = :groupId")
    fun findByGroupIdWithUser(groupId: Long): List<GroupMembership>

    @Query("SELECT gm FROM GroupMembership gm JOIN FETCH gm.group WHERE gm.user.id = :userId")
    fun findByUserIdWithGroups(userId: Long): List<GroupMembership>
}
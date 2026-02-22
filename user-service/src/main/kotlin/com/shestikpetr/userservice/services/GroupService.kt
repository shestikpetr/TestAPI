package com.shestikpetr.userservice.services

import com.shestikpetr.userservice.dto.UpdateGroupRequest
import com.shestikpetr.userservice.entities.Group
import com.shestikpetr.userservice.repositories.GroupRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GroupService(private val groupRepository: GroupRepository) {
    fun createGroup(group: Group): Group = groupRepository.save(group)

    fun getGroupById(id: Long): Group = groupRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id $id not found") }

    fun getAllGroups(): List<Group> = groupRepository.findAll()

    fun updateGroupById(id: Long, request: UpdateGroupRequest) {
        val existingGroup = getGroupById(id)

        request.name?.let { existingGroup.name = it }
        request.description?.let { existingGroup.description = it }
        request.coverUrl?.let { existingGroup.coverUrl = it }

        groupRepository.save(existingGroup)
    }

    fun deleteGroupById(id: Long) {
        groupRepository.deleteById(id)
    }
}
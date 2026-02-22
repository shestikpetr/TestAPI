package com.shestikpetr.userservice.controllers

import com.shestikpetr.userservice.dto.AddMemberRequest
import com.shestikpetr.userservice.dto.UpdateRoleRequest
import com.shestikpetr.userservice.dto.toResponse
import com.shestikpetr.userservice.services.GroupMembershipService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/groups/{groupId}/members")
class GroupMembershipController(private val groupMembershipService: GroupMembershipService) {

    @GetMapping
    fun getMembers(@PathVariable groupId: Long) =
        groupMembershipService.getGroupMembers(groupId).map { it.toResponse() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addMember(
        @PathVariable groupId: Long,
        @RequestBody @Valid request: AddMemberRequest
    ) = groupMembershipService.addMember(groupId, request.userId, request.role).toResponse()

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeMember(
        @PathVariable groupId: Long,
        @PathVariable userId: Long
    ) = groupMembershipService.removeMember(groupId, userId)

    @PutMapping("/{userId}/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeMemberRole(
        @PathVariable groupId: Long,
        @PathVariable userId: Long,
        @RequestBody @Valid request: UpdateRoleRequest
    ) = groupMembershipService.updateRole(groupId, userId, request.role)
}
package com.shestikpetr.userservice.controllers

import com.shestikpetr.userservice.dto.CreateGroupRequest
import com.shestikpetr.userservice.dto.UpdateGroupRequest
import com.shestikpetr.userservice.dto.toEntity
import com.shestikpetr.userservice.dto.toResponse
import com.shestikpetr.userservice.services.GroupService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/groups")
class GroupController(private val groupService: GroupService) {

    @GetMapping
    fun getAll() = groupService.getAllGroups().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long) = groupService.getGroupById(id).toResponse()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CreateGroupRequest) =
        groupService.createGroup(request.toEntity()).toResponse()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody @Valid request: UpdateGroupRequest) =
        groupService.updateGroupById(id, request).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = groupService.deleteGroupById(id)
}
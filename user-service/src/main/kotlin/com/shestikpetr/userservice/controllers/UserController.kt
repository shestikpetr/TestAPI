package com.shestikpetr.userservice.controllers

import com.shestikpetr.userservice.dto.*
import com.shestikpetr.userservice.services.GroupMembershipService
import com.shestikpetr.userservice.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService, private val groupMembershipService: GroupMembershipService) {

    @GetMapping
    fun getAll() = userService.getAllUsers().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long) = userService.getUserById(id).toResponse()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CreateUserRequest) =
        userService.createUser(request.toEntity()).toResponse()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Long, @RequestBody @Valid request: UpdateUserRequest) =
        userService.updateUserById(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = userService.deleteUserById(id)

    @GetMapping("/{id}/groups")
    fun getGroups(@PathVariable id: Long): List<GroupMembershipResponse> =
        groupMembershipService.getUserGroups(id).map { it.toResponse() }
}
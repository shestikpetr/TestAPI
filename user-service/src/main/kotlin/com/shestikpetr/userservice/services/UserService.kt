package com.shestikpetr.userservice.services

import com.shestikpetr.userservice.dto.UpdateUserRequest
import com.shestikpetr.userservice.entities.User
import com.shestikpetr.userservice.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun createUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)!!
        return userRepository.save(user)
    }


    fun getUserById(id: Long): User = userRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found") }


    fun getAllUsers(): List<User> = userRepository.findAll()

    fun updateUserById(id: Long, request: UpdateUserRequest): User {
        val existingUser = getUserById(id)

        request.username?.let { existingUser.username = it }
        request.email?.let { existingUser.email = it }
        request.password?.let { existingUser.password = passwordEncoder.encode(it)!! }
        request.avatarUrl?.let { existingUser.avatarUrl = it }

        return userRepository.save(existingUser)
    }

    fun deleteUserById(id: Long) {
        getUserById(id)
        userRepository.deleteById(id)
    }
}
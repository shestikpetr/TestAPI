package com.shestikpetr.userservice.services

import com.shestikpetr.userservice.entities.User
import com.shestikpetr.userservice.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(user: User) {
        userRepository.save(user)
    }

    fun getUserById(id: Long): User = userRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found") }


    fun getAllUsers(): List<User> = userRepository.findAll()

    fun updateUserById(id: Long, user: User) {
        val existingUser = getUserById(id)
        existingUser.username = user.username
        existingUser.email = user.email
        existingUser.password = user.password
        existingUser.avatarUrl = user.avatarUrl
        existingUser.systemRole = user.systemRole

        userRepository.save(existingUser)
    }

    fun deleteUserById(id: Long) = userRepository.deleteById(id)
}
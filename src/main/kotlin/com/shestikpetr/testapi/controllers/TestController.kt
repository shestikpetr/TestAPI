package com.shestikpetr.testapi.controllers

import com.shestikpetr.testapi.entities.Test
import com.shestikpetr.testapi.repositories.TestRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/tests")
class TestController(private val testRepository: TestRepository) {
    @GetMapping
    fun getAll(): List<Test> = testRepository.findAll()

    @PostMapping
    fun create(@Valid @RequestBody test: Test): Test = testRepository.save(test)

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long) = testRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
}
package com.shestikpetr.testapi.repositories

import com.shestikpetr.testapi.entities.*
import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<Test, Long>
interface QuestionRepository : JpaRepository<Question, Long>
interface AnswerRepository : JpaRepository<Answer, Long>
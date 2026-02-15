package com.shestikpetr.testapi.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotBlank
    var text: String = "",
    var isCorrect: Boolean = false
) {
    fun copy() = Answer(
        text = this.text,
        isCorrect = this.isCorrect
    )
}
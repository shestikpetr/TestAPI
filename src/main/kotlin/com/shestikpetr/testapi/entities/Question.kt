package com.shestikpetr.testapi.entities

import jakarta.persistence.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

@Entity
class Question(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "TEXT")
    @NotBlank
    var content: String = "",

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "question_id")
    @NotEmpty
    @Valid
    var answers: MutableList<Answer> = mutableListOf()
) {
    fun copy() = Question(
        content = this.content,
        answers = this.answers.map { it.copy() }.toMutableList()
    )
}
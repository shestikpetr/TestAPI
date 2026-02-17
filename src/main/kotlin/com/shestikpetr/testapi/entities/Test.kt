package com.shestikpetr.testapi.entities

import jakarta.persistence.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

@Entity
class Test(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotBlank
    var title: String = "",
    var description: String? = null,
    var coverUrl: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    val author: User? = null,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "test_id")
    @NotEmpty
    @Valid
    var questions: MutableList<Question> = mutableListOf()
) {
    fun copy() = Test(
        title = "Копия ${this.title}",
        description = this.description,
        coverUrl = this.coverUrl,
        questions = this.questions.map { it.copy() }.toMutableList(),
        author = this.author,
    )
}
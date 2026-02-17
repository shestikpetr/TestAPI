package com.shestikpetr.testapi.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotBlank
    @Column(unique = true)
    var username: String = "",
    @Column(unique = true)
    @Email
    @NotBlank
    var email: String = "",
    @NotBlank
    var password: String = "",
    @OneToMany(mappedBy = "author")
    var tests: MutableList<Test> = mutableListOf(),
) {
}
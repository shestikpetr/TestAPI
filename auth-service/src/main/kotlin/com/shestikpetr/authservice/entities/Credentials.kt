package com.shestikpetr.authservice.entities

import com.shestikpetr.authservice.enums.SystemRole
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import java.util.*


@Table(name = "credentials")
@Entity
class Credentials(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val userId: UUID,

    @Email
    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false)
    val passwordHash: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: SystemRole = SystemRole.USER
)
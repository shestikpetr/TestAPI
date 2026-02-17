package com.shestikpetr.userservice.entities

import com.shestikpetr.userservice.enums.SystemRole
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Table(name = "users")
@Entity
class User(
    @NotBlank
    @Column(unique = true)
    var username: String = "",
    @Column(unique = true)
    @Email
    @NotBlank
    var email: String = "",
    @NotBlank
    var password: String = "",
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var systemRole: SystemRole = SystemRole.USER,
    var avatarUrl: String? = null,
    @OneToMany(mappedBy = "user")
    var memberships: MutableList<GroupMembership> = mutableListOf()
) : BaseEntity()

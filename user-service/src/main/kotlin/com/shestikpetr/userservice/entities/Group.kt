package com.shestikpetr.userservice.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "groups")
class Group(
    @NotBlank
    @Column(unique = true, nullable = false)
    var name: String = "",
    var description: String? = null,
    var coverUrl: String? = null,
    @OneToMany(mappedBy = "group")
    var memberships: MutableList<GroupMembership> = mutableListOf()
) : BaseEntity()
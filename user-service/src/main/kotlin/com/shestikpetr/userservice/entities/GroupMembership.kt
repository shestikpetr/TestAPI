package com.shestikpetr.userservice.entities

import com.shestikpetr.userservice.enums.GroupRole
import jakarta.persistence.*

@Entity
@Table(name = "group_memberships", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "group_id"])])
class GroupMembership(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    var group: Group,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: GroupRole = GroupRole.MEMBER
) : BaseEntity()
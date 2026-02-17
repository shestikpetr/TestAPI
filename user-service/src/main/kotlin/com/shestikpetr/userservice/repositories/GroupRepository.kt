package com.shestikpetr.userservice.repositories

import com.shestikpetr.userservice.entities.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<Group, Long> {
    fun findByName(name: String): Group?
}
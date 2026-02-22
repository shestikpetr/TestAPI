package com.shestikpetr.authservice.repositories

import com.shestikpetr.authservice.entities.Credentials
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CredentialsRepository : JpaRepository<Credentials, UUID> {
    fun findByEmail(email: String): Credentials?
}
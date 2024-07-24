package com.example.evam2.respository

import com.example.evam2.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<Users, String> {
    fun findByUsername(username: String): Users?
}
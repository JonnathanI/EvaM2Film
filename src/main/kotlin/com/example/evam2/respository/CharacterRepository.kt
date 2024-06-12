package com.example.evam2.respository

import com.example.evam2.model.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<Character, Long> {
    fun findById(id: Long?): Character?
}
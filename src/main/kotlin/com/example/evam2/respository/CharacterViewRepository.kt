package com.example.evam2.respository

import com.example.evam2.model.Character
import com.example.evam2.model.CharacterView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterViewRepository: JpaRepository<CharacterView, Long> {
    fun findById(id:Long?):Character?
}
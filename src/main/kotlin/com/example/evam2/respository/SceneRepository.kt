package com.example.evam2.respository

import com.example.evam2.model.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneRepository: JpaRepository<Scene, Long> {
    fun findById(id: Long?): Scene?
}
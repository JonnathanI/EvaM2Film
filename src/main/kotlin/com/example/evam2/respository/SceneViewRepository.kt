package com.example.evam2.respository

import com.example.evam2.model.Scene
import com.example.evam2.model.SceneView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneViewRepository: JpaRepository<SceneView, Long> {
    fun findById(id: Long?): Scene?
}

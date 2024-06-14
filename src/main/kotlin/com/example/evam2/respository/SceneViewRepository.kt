package com.example.evam2.respository

import com.example.evam2.model.FilmView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneViewRepository: JpaRepository<FilmView, Long> {
    fun findFilmViewById(id: Long?): FilmView?
}

package com.example.evam2.respository

import com.example.evam2.model.Film
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilmRepository: JpaRepository<Film, Long> {
    fun findById(id: Long?): Film?
}
package com.example.evam2.respository

import com.example.evam2.model.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface CharacterRepository: JpaRepository<Character, Long> {
    fun findById(id: Long?): Character?
    @Query("SELECT SUM(s.minutes) FROM Scene s JOIN Character c ON s.id = c.scene.id WHERE s.film = :filmId")
    fun  sumMinutesByFilm(@Param("filmId") filmId: Long): BigDecimal?
}
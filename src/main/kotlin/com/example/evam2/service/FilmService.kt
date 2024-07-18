package com.example.evam2.service

import com.example.evam2.model.Film
import com.example.evam2.model.SceneView
import com.example.evam2.respository.FilmRepository
import com.example.evam2.respository.SceneViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {
    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list(): List<Film> {
        return filmRepository.findAll()
    }
    fun listById(id: Long): Film {
        return filmRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found") }
    }

    fun save(film: Film): Film {
        return filmRepository.save(film)
    }

    fun update(film: Film): Film {
        try {
            filmRepository.findById(film.id) ?: throw Exception("Pelicula no Encontrada")
            return filmRepository.save(film)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTitle(film: Film): Film {
        try {
            val response = filmRepository.findById(film.id) ?: throw Exception("Pelicula no Encontrada")
            response.apply {
                title = film.title
            }
            return filmRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val response = filmRepository.findById(id).orElseThrow {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Pelicula no Existe con el Id: $id")
            }
            filmRepository.delete(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la Pelicula", ex)
        }
    }
}

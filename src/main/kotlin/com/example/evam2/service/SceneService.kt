package com.example.evam2.service


import com.example.evam2.model.Scene
import com.example.evam2.model.SceneView
import com.example.evam2.respository.FilmRepository
import com.example.evam2.respository.SceneRepository
import com.example.evam2.respository.SceneViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var filmRepository: FilmRepository

    @Autowired
    lateinit var sceneViewRepository: SceneViewRepository

    fun listWithFilm(): List<SceneView>{
        return sceneViewRepository.findAll()
    }

    fun list(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene {
        val filmId = scene.film?.id ?: throw Exception("Film ID is required")
        val scenes = sceneRepository.findByFilmId(filmId)
        val film = filmRepository.findById(filmId).orElseThrow { Exception("Film not found") }

        val totalMinutes = scenes.sumByDouble { it.minutes?.toDouble() ?: 0.0 }

        if ((totalMinutes + (scene.minutes?.toDouble() ?: 0.0)) > (film.duration?.toDouble() ?: 0.0)) {
            throw Exception("El total de minutos alcanzado")
        }

        return sceneRepository.save(scene)
    }

    fun update(scene: Scene): Scene {
        try {
            sceneRepository.findById(scene.id ?: throw Exception("Scene ID is required"))
                .orElseThrow { Exception("Escena no encontrada") }
            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateLocation(scene: Scene): Scene {
        try {
            val response = sceneRepository.findById(scene.id ?: throw Exception("Scene ID is required"))
                .orElseThrow { Exception("Escena no encontrada") }
            response.apply {
                location = scene.location
            }
            return sceneRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun listById(id: Long):Scene {
        return sceneRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found") }
    }

    fun delete(id: Long) {
        try {
            val response = sceneRepository.findById(id).orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND, "Escena no existe con el ID: $id")
            }
            sceneRepository.delete(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la escena", ex)
        }
    }

}
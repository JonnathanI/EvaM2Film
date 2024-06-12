package com.example.evam2.service


import com.example.evam2.model.Scene
import com.example.evam2.respository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun list(): List<Scene>{
        return sceneRepository.findAll()
    }

    fun save(scene: Scene):Scene{
        return sceneRepository.save(scene)
    }

    fun update(scene: Scene):Scene{
        try {
            sceneRepository.findById(scene.id)?: throw Exception("Ecena  no Encontrado")
            return sceneRepository.save(scene)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateLocation(scene: Scene):Scene{
        try {

            var response = sceneRepository.findById(scene.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                location=scene.location

            }
            return sceneRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = sceneRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Ecena no Existe con el Id:  $id")}
            sceneRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la Esena", ex)
        }
    }

}
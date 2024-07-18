package com.example.evam2.controller


import com.example.evam2.model.Scene
import com.example.evam2.model.SceneView
import com.example.evam2.service.SceneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scene")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class SceneController {
    @Autowired
    lateinit var sceneService: SceneService

    @GetMapping
    fun list(): List<Scene>{
        return sceneService.list()
    }
    @GetMapping("scene-view")
    fun listWithFilm(): ResponseEntity<List<SceneView>> {
        val scene = sceneService.listWithFilm()
        return ResponseEntity(scene, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): Scene {
        return sceneService.listById(id)
    }

    @PostMapping
    fun save(@RequestBody scene: Scene):Scene{
        return sceneService.save(scene)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody scene: Scene?): ResponseEntity<Scene> {
        val updatedScene= sceneService.update(scene!!)
        return ResponseEntity.ok(updatedScene)
    }

    @PatchMapping("/{id}")
    fun updateLocation(@PathVariable id: Long?, @RequestBody scene: Scene?): ResponseEntity<Scene> {
        val updatedScene = sceneService.updateLocation(scene!!)
        return ResponseEntity.ok(updatedScene)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<String>{
        sceneService.delete(id)
        return ResponseEntity("Escena  Eliminada",HttpStatus.OK)
    }
}
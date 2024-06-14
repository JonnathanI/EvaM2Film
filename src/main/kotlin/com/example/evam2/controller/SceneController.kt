package com.example.evam2.controller


import com.example.evam2.model.Scene
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
    @PostMapping
    fun save(@RequestBody scene: Scene):Scene{
        return sceneService.save(scene)
    }
    @PutMapping
    fun update(@RequestBody scene: Scene): ResponseEntity <Scene>{
        return ResponseEntity(sceneService.update(scene), HttpStatus.OK)
    }
    @PatchMapping
    fun updateTitle (@RequestBody scene: Scene): ResponseEntity<Scene>{
        return ResponseEntity(sceneService.updateLocation(scene),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<String>{
        sceneService.delete(id)
        return ResponseEntity("Escena  Eliminada",HttpStatus.OK)
    }
}
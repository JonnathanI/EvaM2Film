package com.example.evam2.controller

import com.example.evam2.model.Character
import com.example.evam2.service.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/character")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class CharacterController {
    @Autowired
    lateinit var characterService: CharacterService

    @GetMapping
    fun list(): List<Character>{
        return characterService.list()
    }
    @GetMapping("/characterView")
    fun listCharacter(): ResponseEntity<List<Character>>{
        val scene = characterService.list()
        return ResponseEntity(scene,HttpStatus.OK)
    }
    @PostMapping
    fun save(@RequestBody character: Character):Character{
        return characterService.save(character)
    }
    @PutMapping
    fun update(@RequestBody character: Character): ResponseEntity <Character>{
        return ResponseEntity(characterService.update(character), HttpStatus.OK)
    }
    @PatchMapping
    fun updateTitle (@RequestBody character: Character): ResponseEntity<Character>{
        return ResponseEntity(characterService.updateName(character),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<String>{
        characterService.delete(id)
        return ResponseEntity("Personaje Eliminado",HttpStatus.OK)
    }
}
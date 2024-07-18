package com.example.evam2.controller

import com.example.evam2.model.Character
import com.example.evam2.service.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
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

    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): Character{
        return characterService.listById(id)
    }


    @PostMapping
    fun save(@RequestBody character: Character):Character{
        return characterService.save(character)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?  ,@RequestBody character: Character?): ResponseEntity <Character>{
        val updatedCharacters =  characterService.update(character!!)
        return ResponseEntity.ok(updatedCharacters)
    }
    @PatchMapping("/{id}")
    fun updateTitle (@PathVariable id: Long?  ,@RequestBody character: Character?): ResponseEntity<Character>{
        val updatedCharacters =  characterService.updateName(character!!)
        return ResponseEntity.ok(updatedCharacters)
    }

    @GetMapping("/validateCost/{filmId}")
    fun validateFimDuration(@PathVariable filmId: Long):ResponseEntity<String>{
        try {
            characterService.validateCostFilmDuration(filmId)
            return ResponseEntity.ok("El costo total de los personajes no excede la duración de la película.")
        }catch (ex: Exception){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<String>{
        characterService.delete(id)
        return ResponseEntity("Personaje Eliminado",HttpStatus.OK)
    }
}
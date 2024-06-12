package com.example.evam2.service

import com.example.evam2.model.Character
import com.example.evam2.respository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var characterRepository: CharacterRepository

    fun list(): List<Character>{
        return characterRepository.findAll()
    }

    fun save(character: Character):Character{
        return characterRepository.save(character)
    }

    fun update(character: Character):Character{
        try {
            characterRepository.findById(character.id)?: throw Exception("Personajes no Encontrado")
            return characterRepository.save(character)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(character: Character):Character{
        try {

            var response = characterRepository.findById(character.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                name=character.name

            }
            return characterRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = characterRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Personaje no Existe con el Id:  $id")}
            characterRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el Personaje", ex)
        }
    }

}
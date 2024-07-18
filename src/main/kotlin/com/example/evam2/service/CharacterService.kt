package com.example.evam2.service

import com.example.evam2.model.Character
import com.example.evam2.model.CharacterView
import com.example.evam2.respository.CharacterRepository
import com.example.evam2.respository.CharacterViewRepository
import com.example.evam2.respository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@Service
class CharacterService {
    @Autowired
    lateinit var characterRepository: CharacterRepository

    @Autowired
    lateinit var characterViewRepository: CharacterViewRepository

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list(): List<Character>{
        return characterRepository.findAll()
    }

    fun listCharacter(): List<CharacterView>{
        return characterViewRepository.findAll()
    }

    fun listById(id: Long):Character{
        return characterRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found.") }
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

    fun validateCostFilmDuration(filmId:Long){
        val film = filmRepository.findById(filmId)
            .orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found")}
        val totalMinutes = characterRepository.sumMinutesByFilm(filmId)
            ?: BigDecimal.ZERO
        if(totalMinutes>film.duration){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El Costo Total de los Actores es mayor a la duracion de la Pelicula  ")
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
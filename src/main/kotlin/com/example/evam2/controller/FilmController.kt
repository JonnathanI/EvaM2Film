import com.example.evam2.model.Film
import com.example.evam2.service.FilmService
import org.springframework.beans.factory.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/film")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list(): List<Film> {
        return filmService.list()
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): Film {
        return filmService.listById(id)
    }

    @PostMapping
    fun save(@RequestBody film: Film): ResponseEntity<Film> {
        if (film.releaseYear == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
        return ResponseEntity(filmService.save(film), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody film: Film?): ResponseEntity<Film> {
        val updatedFilm = filmService.update(film!!)
        return ResponseEntity.ok(updatedFilm)
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long?, @RequestBody film: Film?): ResponseEntity<Film> {
        val updatedFilm = filmService.updateTitle(film!!)
        return ResponseEntity.ok(updatedFilm)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        filmService.delete(id)
        return ResponseEntity("Pelicula Eliminada", HttpStatus.OK)
    }
}

package com.example.evam2.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "film")
class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var title: String? = null
    var director: String? = null
    var duration: BigDecimal? = null
    @Column(name = "release_year", nullable = false)
    var releaseYear: Int? = null // Cambiado a Integer
    var genre: String? = null
    var language: String? = null
    var country: String? = null
    var synopsis: String? = null
}

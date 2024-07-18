package com.example.evam2.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "scene_view")
class SceneView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var budget: Double? = null
    var minutes: BigDecimal? = null
    var location: String? = null
    @Column(name = "date_shot")
    var dateShot: String? = null
    @Column(name = "actors_involved")
    var actorsInvolved: String? = null
    @Column(name = "film_title")
    val filmTitle: String?= null
}
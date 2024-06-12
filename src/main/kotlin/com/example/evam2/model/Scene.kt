package com.example.evam2.model

import jakarta.persistence.*

@Entity
@Table(name = "scene")
class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var budget: Double? = null
    var minutes: Double? = null
    var location: String? = null
    @Column(name = "date_shot")
    var dateShot: String? = null
    @Column(name = "actors_involved")
    var actorsInvolved: String? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    var film:Film? = null
}
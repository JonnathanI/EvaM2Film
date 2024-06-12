package com.example.evam2.model

import jakarta.persistence.*

@Entity
@Table(name = "character")
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var name: String? = null
    var description: String? = null
    var cost: Double? = null
    var actor: String? = null
    var stock: String? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id")
    var scene:Scene? = null
}
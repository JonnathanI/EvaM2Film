package com.example.evam2.model

import jakarta.persistence.*

@Entity
@Table( name = "character_view")
class CharacterView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var name: String? = null
    var description: String? = null
    var cost: Double? = null
    var actor: String? = null
    var stock: String? = null
}
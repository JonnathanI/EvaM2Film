package com.example.evam2.model

import jakarta.persistence.*

@Entity
@Table(name = "role")
class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var rol: String? = null
    @Column(name = "user_id")
    var userId: Long? = null
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
    var users:Users? = null
}
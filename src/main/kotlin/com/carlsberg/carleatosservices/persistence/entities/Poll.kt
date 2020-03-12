package com.carlsberg.carleatosservices.persistence.entities

import java.util.*
import java.util.UUID.randomUUID
import javax.persistence.*

@Entity(name = "poll")
data class Poll(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column
        val eid: UUID,

        @OneToMany
        val restaurants: Collection<Restaurant>


) {
    companion object {
        fun from(id: Int, restaurants: Collection<Restaurant>) {
            Poll(id, randomUUID(), restaurants)
        }
    }

}

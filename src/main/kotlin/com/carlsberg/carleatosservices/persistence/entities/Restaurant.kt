package com.carlsberg.carleatosservices.persistence.entities

import java.util.*
import java.util.UUID.randomUUID
import javax.persistence.*

@Entity(name = "restaurant")
data class Restaurant(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(nullable = false)
        val eid: UUID?,

        @Column(nullable = false)
        val name: String,

        @Column
        val nickname: String?,

        @Column
        val description: String?,

        @Column(nullable = false)
        @Enumerated(value = EnumType.STRING)
        val type: RestaurantType


) {
    override fun toString(): String {
        return "Restaurant(id=$id, name='$name')"
    }

    companion object {
        fun from(id: Int, name: String, type: RestaurantType) {
            Restaurant(id, randomUUID(), name, null, null, type)
        }
    }
}

enum class RestaurantType {
    TASCA, FANCY, SUSHI, SPICY, REGULAR
}

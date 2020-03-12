package com.carlsberg.carleatosservices.web.dto

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import org.springframework.hateoas.server.core.Relation
import java.util.*

@Relation(itemRelation = "restaurant", collectionRelation = "restaurants")
data class RestaurantDto(val eid: UUID, val name: String, val nickname: String?, val description: String?, val type: String) {

    companion object {
        fun from(restaurant: Restaurant) =
                RestaurantDto(
                        eid = restaurant.eid,
                        name = restaurant.name,
                        nickname = restaurant.nickname,
                        description = restaurant.description,
                        type = restaurant.type.name)

    }
}
package com.carlsberg.carleatosservices.web.dto

import com.carlsberg.carleatosservices.persistence.entities.Poll
import org.springframework.hateoas.server.core.Relation
import java.util.*
import java.util.stream.Collectors.toList

@Relation(itemRelation = "poll", collectionRelation = "polls")
data class PollDto(val eid: UUID, val restaurants: Collection<RestaurantDto>) {

    companion object {
        fun from(poll: Poll) =
                PollDto(
                        eid = poll.eid,
                        restaurants = poll.restaurants.stream()
                                .map { RestaurantDto.from(it) }
                                .collect(toList())
                )

    }
}

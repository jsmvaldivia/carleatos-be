package com.carlsberg.carleatosservices.services

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import com.carlsberg.carleatosservices.persistence.repositories.RestaurantRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RestaurantService(val restaurantRepo: RestaurantRepository) {

    fun findByEid(eid: UUID): Restaurant = restaurantRepo.findByEid(eid)

    fun findAll(): Collection<Restaurant> = restaurantRepo.findAll()

}

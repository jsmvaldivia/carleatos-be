package com.carlsberg.carleatosservices.services

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import com.carlsberg.carleatosservices.persistence.repositories.RestaurantRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RestaurantServiceImpl(val restaurantRepo: RestaurantRepository) : RestaurantService {

    override fun findByEid(eid: UUID): Restaurant = restaurantRepo.findByEid(eid)

    override fun findAll(): Collection<Restaurant> = restaurantRepo.findAll()

}

package com.carlsberg.carleatosservices.services

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import java.util.*

interface RestaurantService {
    fun findByEid(eid: UUID): Restaurant
    fun findAll(): Collection<Restaurant>
}
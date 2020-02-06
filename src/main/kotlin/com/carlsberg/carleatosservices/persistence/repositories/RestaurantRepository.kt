package com.carlsberg.carleatosservices.persistence.repositories

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RestaurantRepository : JpaRepository<Restaurant, Long> {

    fun findByEid(eid: UUID): Restaurant
}

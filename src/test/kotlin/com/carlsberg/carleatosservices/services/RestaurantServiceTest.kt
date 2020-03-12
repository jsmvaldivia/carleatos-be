package com.carlsberg.carleatosservices.services

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import com.carlsberg.carleatosservices.persistence.entities.RestaurantType.TASCA
import com.carlsberg.carleatosservices.persistence.repositories.RestaurantRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsIterableContaining.hasItem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.UUID.randomUUID


internal class RestaurantServiceTest {

    private lateinit var restaurantService: RestaurantService

    private val restaurantRepository = mock(RestaurantRepository::class.java)


    @BeforeEach
    internal fun setUp() {
        restaurantService = RestaurantService(restaurantRepository)
    }

    @Test
    fun `given an eid, when calling service to find restaurant by eid, then assert that found restaurant is equal retrieved one`() {
        val eid = randomUUID()

        `when`(restaurantRepository.findByEid(eid = eid))
                .thenReturn(Restaurant(id = 1, eid = eid, name = "Ramiro", nickname = "Zé e Maria", description = "", type = TASCA))

        val foundRestaurant = restaurantService.findByEid(eid)
        assertThat(foundRestaurant, `is`(equalTo(Restaurant(1, eid, "Ramiro", "Zé e Maria", "", TASCA))))
    }

    @Test
    fun `when calling service to find all restaurants,then assert that found restaurants has item equal to expected`() {
        val eid = randomUUID()

        `when`(restaurantRepository.findAll())
                .thenReturn(listOf(Restaurant(id = 1, eid = eid, name = "Ramiro", nickname = "Zé e Maria", description = "", type = TASCA)))

        val foundRestaurants = restaurantService.findAll()
        assertThat(foundRestaurants, hasItem(equalTo(Restaurant(1, eid, "Ramiro", "Zé e Maria", "", TASCA))))
    }
}
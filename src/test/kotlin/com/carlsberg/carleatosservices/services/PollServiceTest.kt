package com.carlsberg.carleatosservices.services

import com.carlsberg.carleatosservices.persistence.entities.Poll
import com.carlsberg.carleatosservices.persistence.entities.Restaurant
import com.carlsberg.carleatosservices.persistence.entities.RestaurantType.TASCA
import com.carlsberg.carleatosservices.persistence.repositories.PollRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsIterableContaining.hasItem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Collections.singletonList
import java.util.UUID.randomUUID

internal class PollServiceTest {

    private lateinit var pollService: PollService

    private val pollRepository = mock(PollRepository::class.java)

    @BeforeEach
    internal fun setUp() {
        pollService = PollService(pollRepository)
    }

    @Test
    fun `given a poll external id, when calling service to find a poll then assert that found restaurant is equal retrieved one`() {
        val pollEid = randomUUID()

        `when`(pollRepository.findByEid(pollEid))
                .thenReturn(Poll(id = 1, eid = pollEid,
                        restaurants = singletonList(Restaurant(id = 1, eid = pollEid, name = "Ramiro", nickname = "Zé e Maria", description = "", type = TASCA))))

        val foundPoll = pollService.findPollByEid(pollEid)

        assertThat(foundPoll.restaurants,
                hasItem(equalTo(Restaurant(id = 1, eid = pollEid, name = "Ramiro", nickname = "Zé e Maria", description = "", type = TASCA))))
    }

    @Test
    fun findActivePolls() {
    }

    @Test
    fun findAllPolls() {
    }

    @Test
    fun answerPoll() {
    }
}
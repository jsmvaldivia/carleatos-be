package com.carlsberg.carleatosservices.persistence.repositories

import com.carlsberg.carleatosservices.persistence.entities.Poll
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PollRepository : JpaRepository<Poll, Long> {

    fun findByEid(eid: UUID): Poll
}

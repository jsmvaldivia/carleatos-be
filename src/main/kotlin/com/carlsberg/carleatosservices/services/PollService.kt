package com.carlsberg.carleatosservices.services

import com.carlsberg.carleatosservices.persistence.entities.Poll
import com.carlsberg.carleatosservices.persistence.entities.PollAnswer
import com.carlsberg.carleatosservices.persistence.repositories.PollRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PollService(val pollRepository: PollRepository) {


    fun findPollByEid(eid: UUID): Poll {
        return pollRepository.findByEid(eid)
    }

    fun findActivePolls(): Collection<Poll> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findAllPolls(): Collection<Poll> {
        return pollRepository.findAll();
    }

    fun answerPoll(answer: PollAnswer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

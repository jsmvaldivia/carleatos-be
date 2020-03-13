package com.carlsberg.carleatosservices.persistence.entities

import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*
import java.util.UUID.randomUUID
import javax.persistence.*

@Entity(name = "poll")
data class Poll(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column
        val eid: UUID,

        @Column
        val startDate: LocalDateTime,

        @Column
        val endDate: LocalDateTime?,

        @Column
        val blocked: Boolean,

        @ManyToMany
        @JoinTable(
                name = "poll_restaurant",
                joinColumns = [JoinColumn(name = "poll_id")],
                inverseJoinColumns = [JoinColumn(name = "restaurant_id")])
        val restaurants: Collection<Restaurant>


) {

    fun isActive(): Boolean {
        return blocked
    }

    companion object {
        fun todayAtLunchTime(id: Int, restaurants: Collection<Restaurant>) {
            val todayAt12 = now().withHour(12)
            Poll(id, randomUUID(), todayAt12, todayAt12.plusHours(2L), false, restaurants)
        }

        fun from(id: Int, startDate: LocalDateTime, endDate: LocalDateTime?, blocked: Boolean, restaurants: Collection<Restaurant>) {
            Poll(id, randomUUID(), startDate, endDate, blocked, restaurants)
        }
    }

}

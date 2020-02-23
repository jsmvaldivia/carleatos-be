package com.carlsberg.carleatosservices.web.dto

import org.springframework.hateoas.server.core.Relation

@Relation(itemRelation = "restaurant", collectionRelation = "restaurants")
data class RestaurantDto(val eid: String, val name: String, val nickname: String?, val description: String?, val type: String)
package com.carlsberg.carleatosservices.web.controllers

import com.carlsberg.carleatosservices.services.RestaurantService
import com.carlsberg.carleatosservices.web.dto.RestaurantDto
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.ExposesResourceFor
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors.toList

@RestController
@ExposesResourceFor(value = RestaurantDto::class)
@RequestMapping("/api/restaurants")
class RestaurantController(val restaurantService: RestaurantService) {

    @GetMapping("/{eid}")
    fun findById(@PathVariable eid: UUID): EntityModel<RestaurantDto> {
        val restaurant = restaurantService.findByEid(eid)
        val linkToSelf = linkTo(methodOn(RestaurantController::class.java).findById(eid)).withSelfRel()
        return EntityModel.of(RestaurantDto.from(restaurant), linkToSelf)
    }

    @GetMapping
    fun findAll(): CollectionModel<EntityModel<RestaurantDto>> {
        val linkToSelf = linkTo(methodOn(this::class.java).findAll()).withSelfRel()
        val restaurantList = restaurantService.findAll().stream()
                .map { RestaurantDto.from(it) }
                .map { it.toEntityModel() }
                .collect(toList())

        return CollectionModel.of(restaurantList, linkToSelf)
    }

    fun RestaurantDto.toEntityModel(): EntityModel<RestaurantDto> {
        return EntityModel.of(this, linkTo(methodOn(RestaurantController::class.java).findById(this.eid)).withSelfRel())
    }
}
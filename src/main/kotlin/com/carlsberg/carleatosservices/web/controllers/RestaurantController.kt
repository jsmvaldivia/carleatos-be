package com.carlsberg.carleatosservices.web.controllers

import com.carlsberg.carleatosservices.persistence.entities.Restaurant
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
@RequestMapping("/api")
class RestaurantController(val restaurantService: RestaurantService) {

    @GetMapping("/restaurants/{id}")
    fun findById(@PathVariable id: String): EntityModel<RestaurantDto> {
        val linkToSelf = linkTo(methodOn(this::class.java).findById(id)).withSelfRel()
        return EntityModel.of(restaurantService.findByEid(UUID.fromString(id)).toRestaurantDto(),
                linkToSelf)
    }

    @GetMapping("/restaurants")
    fun findAll(): CollectionModel<EntityModel<RestaurantDto>> {
        val linkToSelf = linkTo(methodOn(this::class.java).findAll()).withSelfRel()
        return CollectionModel.of(
                restaurantService.findAll().stream()
                        .map { it.toRestaurantDto() }
                        .map { it.toEntityModel() }
                        .collect(toList())
                , linkToSelf)
    }

    fun Restaurant.toRestaurantDto() = RestaurantDto(
            eid = eid.toString(),
            name = name,
            nickname = nickname,
            description = description,
            type = type.name
    )

    fun RestaurantDto.toEntityModel(): EntityModel<RestaurantDto> {
        return EntityModel.of(this, linkTo(methodOn(RestaurantController::class.java).findById(this.eid)).withSelfRel())
    }
}
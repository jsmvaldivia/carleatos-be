package com.carlsberg.carleatosservices.web.controllers

import com.carlsberg.carleatosservices.services.PollService
import com.carlsberg.carleatosservices.web.dto.PollDto
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
@ExposesResourceFor(value = PollDto::class)
@RequestMapping("/api/polls")
class PollController(val pollService: PollService) {


    @GetMapping("/{eid}")
    fun findById(@PathVariable eid: UUID): EntityModel<PollDto> {
        val poll = pollService.findPollByEid(eid)
        return PollDto.from(poll).toEntityModel()
    }

    @GetMapping
    fun findAll(@PathVariable eid: UUID): CollectionModel<EntityModel<PollDto>> {
        val pollsDto = pollService.findAllPolls().stream()
                .map { PollDto.from(it) }
                .map { it.toEntityModel() }
                .collect(toList())

        val linkToSelf = linkTo(methodOn(PollController::class.java).findById(eid)).withSelfRel()

        return CollectionModel.of(pollsDto, linkToSelf)
    }

    fun PollDto.toEntityModel(): EntityModel<PollDto> {
        val selfRel = linkTo(methodOn(PollController::class.java).findById(this.eid)).withSelfRel()

        return EntityModel.of(this, selfRel)
    }

}
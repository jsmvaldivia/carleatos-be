package com.carlsberg.carleatosservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.hateoas.config.EnableHypermediaSupport

@SpringBootApplication
@EnableHypermediaSupport(type = [EnableHypermediaSupport.HypermediaType.HAL])
class CarleatosServicesApplication

fun main(args: Array<String>) {
    runApplication<CarleatosServicesApplication>(*args)
}

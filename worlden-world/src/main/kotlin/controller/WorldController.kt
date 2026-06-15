package com.worlden.controller

import com.worlden.application.WorldService
import com.worlden.controller.request.CreateWorldRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/world")
class WorldController(
    val worldService: WorldService
) {

    @PostMapping()
    fun create(@RequestBody request: CreateWorldRequest) : ResponseEntity<Any> =
        worldService.createWorld(request).fold(
            ifLeft = { er -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er.toString()) },
            ifRight = { event -> ResponseEntity.status(HttpStatus.CREATED).body(event.toString()) }
        )

}
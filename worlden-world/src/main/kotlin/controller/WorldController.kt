package com.worlden.controller

import com.worlden.application.WorldService
import com.worlden.application.command.CreateWorldCommand
import com.worlden.application.event.WorldCreatedEvent
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
    private val worldService: WorldService
) {

    @PostMapping
    fun create(@RequestBody request: CreateWorldRequest): ResponseEntity<WorldCreatedEvent> {
        val command = CreateWorldCommand(request.worldName, request.worldDescription, request.genres)
        val event = worldService.createWorld(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(event)
    }
}
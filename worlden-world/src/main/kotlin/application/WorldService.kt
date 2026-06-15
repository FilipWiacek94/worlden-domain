package com.worlden.application

import com.worlden.application.command.CreateWorldCommand
import com.worlden.application.event.WorldCreatedEvent
import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import com.worlden.domain.exception.WorldValidationException
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import kotlin.uuid.Uuid

@Service
class WorldService(
    private val worldRepository: WorldRepository,
    private val eventPublisher: ApplicationEventPublisher
) {

    fun createWorld(command: CreateWorldCommand): WorldCreatedEvent {
        if (worldRepository.getByName(command.worldName.trim()) != null) {
            throw WorldValidationException("World name '${command.worldName}' already exists.")
        }
        val world = World.create(Uuid.generateV4(), command.worldName, command.worldDescription, command.genres)
        //TODO: should be atomic
        worldRepository.save(world)
        val event = WorldCreatedEvent(Uuid.generateV4(), world.id())
        eventPublisher.publishEvent(event)
        return event
    }
}
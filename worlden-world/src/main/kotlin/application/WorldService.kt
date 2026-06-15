package com.worlden.application

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.worlden.application.command.CreateWorldCommand
import com.worlden.application.event.WorldCreatedEvent
import com.worlden.application.exception.WorldError
import com.worlden.controller.request.CreateWorldRequest
import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import kotlin.uuid.Uuid

@Service
class WorldService(
    private val worldRepository: WorldRepository,
    private val eventPublisher: ApplicationEventPublisher
) {

    fun createWorld(request: CreateWorldRequest): Either<WorldError, WorldCreatedEvent> = either {

        ensure(worldRepository.getByName(request.worldName) == null) {
            WorldError.InvalidWorldData("World name '${request.worldName}' already exists.")
        }

        val cmd = CreateWorldCommand(request.worldName, request.worldDescription, request.genres)

        val world = try {
            World.create(Uuid.generateV4(), cmd.worldName, cmd.worldDescription, cmd.genres)
        } catch (e: IllegalArgumentException) {
            raise(WorldError.InvalidWorldData(e.localizedMessage))
        }
        worldRepository.save(world)
        val worldCreatedEvent = WorldCreatedEvent(Uuid.generateV4(), world.id())
        eventPublisher.publishEvent(worldCreatedEvent)
        worldCreatedEvent
    }
}
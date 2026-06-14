package com.worlden.application

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.worlden.application.command.CreateWorldCommand
import com.worlden.application.event.WorldCreatedEvent
import com.worlden.application.exception.WorldError
import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import org.springframework.stereotype.Service
import kotlin.uuid.Uuid

@Service
class WorldService(
    private val worldRepository: WorldRepository
) {

    fun createWorld(command: CreateWorldCommand): Either<WorldError, WorldCreatedEvent> = either {

        ensure(worldRepository.getByName(command.worldName) == null) {
            WorldError.InvalidWorldData("World name '${command.worldName}' already exists.")
        }

        val world = try {
            World.create(Uuid.generateV4(), command.worldName, command.worldDescription, command.genres)
        } catch (e: IllegalArgumentException) {
            raise(WorldError.InvalidWorldData(e.localizedMessage))
        }
        worldRepository.save(world)
        WorldCreatedEvent(world.worldId.id, world)
    }
}
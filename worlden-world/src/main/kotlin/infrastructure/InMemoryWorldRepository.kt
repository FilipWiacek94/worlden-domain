package com.worlden.infrastructure

import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import org.springframework.stereotype.Repository
import kotlin.uuid.Uuid

@Repository
class InMemoryWorldRepository : WorldRepository {

    var worlds = mutableMapOf<Uuid, World>()

    override fun save(world: World): World {
        worlds[world.worldId.id] = world
        return world
    }
}
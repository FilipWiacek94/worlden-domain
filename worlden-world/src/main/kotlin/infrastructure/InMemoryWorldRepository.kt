package com.worlden.infrastructure

import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import kotlin.uuid.Uuid

@Repository
class InMemoryWorldRepository : WorldRepository {

    private val worlds = ConcurrentHashMap<Uuid, World>()

    override fun save(world: World): World {
        worlds[world.worldId.id] = world
        return world
    }

    override fun getByName(name: String): World? {
        return worlds.values.find { it.name() == name }
    }
}
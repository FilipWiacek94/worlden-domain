package com.worlden.world.domain

import com.worlden.world.domain.vo.Genre
import com.worlden.world.domain.vo.WorldDescription
import com.worlden.world.domain.vo.WorldId
import com.worlden.world.domain.vo.WorldName
import kotlin.uuid.Uuid

@ConsistentCopyVisibility
data class World private constructor(val worldId: WorldId,
                                     val worldName: WorldName,
                                     val worldDescription: WorldDescription,
                                     val genres: MutableSet<Genre> = mutableSetOf()) {
    companion object {
        fun create(worldId: Uuid, worldName: String, worldDescription: String ,genres: MutableSet<Genre>): World {
            return World(WorldId(worldId), WorldName(worldName), WorldDescription(worldDescription), genres)
        }
    }
}
package com.worlden.domain

import com.worlden.domain.vo.Genre
import com.worlden.domain.vo.WorldDescription
import com.worlden.domain.vo.WorldId
import com.worlden.domain.vo.WorldName
import kotlin.uuid.Uuid

@ConsistentCopyVisibility
data class World private constructor(val worldId: WorldId,
                                     val worldName: WorldName,
                                     val worldDescription: WorldDescription,
                                     val genres: MutableSet<Genre> = mutableSetOf()) {
    companion object {
        fun create(worldId: Uuid, worldName: String, worldDescription: String, genres: MutableSet<Genre>): World {
            return World(WorldId(worldId), WorldName(worldName), WorldDescription(worldDescription), genres)
        }
    }

    fun name(): String = worldName.name
}
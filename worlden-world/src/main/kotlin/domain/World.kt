package com.worlden.domain

import com.worlden.domain.vo.Genre
import com.worlden.domain.vo.WorldDescription
import com.worlden.domain.vo.WorldId
import com.worlden.domain.vo.WorldName
import kotlin.uuid.Uuid

@ConsistentCopyVisibility
data class World private constructor(val worldId: WorldId,
                                     var worldName: WorldName,
                                     var worldDescription: WorldDescription,
                                     var genres: MutableSet<Genre>) {
    companion object {
        fun create(worldId: Uuid, worldName: String, worldDescription: String, genres: MutableSet<Genre>): World {
            return World(WorldId(worldId), WorldName(worldName), WorldDescription(worldDescription), genres)
        }
    }

    fun name(): String = worldName.name

    fun id(): Uuid = worldId.id
}
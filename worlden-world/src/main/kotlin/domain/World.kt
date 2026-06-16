package com.worlden.domain

import com.worlden.domain.vo.Genre
import com.worlden.domain.vo.WorldDescription
import com.worlden.domain.vo.WorldId
import com.worlden.domain.vo.WorldName
import kotlin.uuid.Uuid

@ConsistentCopyVisibility
data class World private constructor(
    private val worldId: WorldId,
    private var worldName: WorldName,
    private var worldDescription: WorldDescription,
    private var genres: Set<Genre>
) {
    companion object {
        fun create(
            worldId: Uuid,
            worldName: String,
            worldDescription: String,
            genres: Set<Genre>
        ): World {
            val name = WorldName.of(worldName)
            val description = WorldDescription.of(worldDescription)
            return World(WorldId(worldId), name, description, genres.toSet())
        }
    }

    fun name(): String = worldName.name

    fun id(): Uuid = worldId.id
}
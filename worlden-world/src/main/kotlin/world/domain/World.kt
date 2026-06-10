package com.worlden.world.domain

import com.worlden.world.domain.vo.Genre
import com.worlden.world.domain.vo.WorldDescription
import com.worlden.world.domain.vo.WorldId
import com.worlden.world.domain.vo.WorldName

data class World(val worldId: WorldId, val worldName: WorldName, val worldDescription: WorldDescription, val genres: MutableSet<Genre> = mutableSetOf()) {
}
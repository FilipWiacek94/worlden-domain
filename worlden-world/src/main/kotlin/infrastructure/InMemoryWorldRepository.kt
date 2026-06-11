package com.worlden.infrastructure

import com.worlden.domain.World
import com.worlden.domain.WorldRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryWorldRepository : WorldRepository {

    override fun save(world: World): World {
        TODO("Not yet implemented")
    }
}
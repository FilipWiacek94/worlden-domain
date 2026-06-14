package com.worlden.domain

import kotlin.uuid.Uuid

interface WorldRepository {
    fun save(world: World): World
    fun getById(id: Uuid): World?
}
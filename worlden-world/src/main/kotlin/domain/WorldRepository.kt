package com.worlden.domain

interface WorldRepository {
    fun save(world: World): World
}
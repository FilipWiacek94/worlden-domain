package com.worlden.world.domain.vo

@JvmInline
value class WorldName(val name: String) {
    init {
        require(name.isNotEmpty()) { "name must not be empty" }
        require(name.isNotBlank()) { "name must not be blank" }
        require(name.length in 5..20) { "name length must be between 5 and 20" }
    }
}
package com.worlden.domain.vo

@JvmInline
value class WorldDescription(val description: String) {
    init {
        require(description.isNotEmpty()) { "description must not be empty" }
        require(description.isNotBlank()) { "description must not be blank" }
        require(description.length in 10..1000) { "description must be between 10 and 1000" }
    }
}
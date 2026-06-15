package com.worlden.domain.vo

import com.worlden.domain.exception.WorldValidationException

@JvmInline
value class WorldDescription(val description: String) {
    companion object {
        fun of(description: String): WorldDescription {
            if (description.isBlank()) throw WorldValidationException("World description must not be blank or empty")
            if (description.length !in 10..1000) throw WorldValidationException("World description length must be between 10 and 1000")
            return WorldDescription(description)
        }
    }
}
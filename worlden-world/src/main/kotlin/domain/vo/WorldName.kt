package com.worlden.domain.vo

import com.worlden.domain.exception.WorldValidationException

@JvmInline
value class WorldName(val name: String) {
    companion object {
        fun of(name: String): WorldName {
            val trimmed = name.trim()
            if (trimmed.isBlank()) throw WorldValidationException("World name must not be blank or empty")
            if (trimmed.length !in 5..20) throw WorldValidationException("World name length must be between 5 and 20")
            return WorldName(trimmed)
        }
    }
}
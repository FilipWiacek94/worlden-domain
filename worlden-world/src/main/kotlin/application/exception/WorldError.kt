package com.worlden.application.exception

sealed class WorldError{
    data class WorldNotFound(val worldName: String) : WorldError()
    data class InvalidWorldData(val name: String) : WorldError()
}
package com.worlden.application.event

import com.worlden.domain.World
import kotlin.uuid.Uuid

data class WorldCreatedEvent(val eventId: Uuid, val worldId: Uuid) {
}
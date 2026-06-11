package com.worlden.domain.vo

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class WorldId @OptIn(ExperimentalUuidApi::class) constructor(val id: Uuid) {
}
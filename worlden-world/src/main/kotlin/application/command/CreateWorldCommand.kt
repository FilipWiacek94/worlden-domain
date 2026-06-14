package com.worlden.application.command

import com.worlden.domain.vo.Genre
import kotlin.uuid.Uuid

data class CreateWorldCommand(val worldName: String, val worldDescription: String, val genres: MutableSet<Genre>) {
}
package com.worlden.application.command

import com.worlden.domain.vo.Genre

data class CreateWorldCommand(val worldName: String, val worldDescription: String, val genres: Set<Genre>) {
}
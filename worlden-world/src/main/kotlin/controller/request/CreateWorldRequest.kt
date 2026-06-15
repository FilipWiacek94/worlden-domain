package com.worlden.controller.request

import com.worlden.domain.vo.Genre

data class CreateWorldRequest(val worldName: String, val worldDescription: String, val genres: Set<Genre>) {
}
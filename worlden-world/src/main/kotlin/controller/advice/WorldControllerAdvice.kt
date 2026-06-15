package com.worlden.controller.advice

import com.worlden.controller.response.ErrorResponse
import com.worlden.domain.exception.WorldValidationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WorldControllerAdvice {

    @ExceptionHandler(WorldValidationException::class)
    fun handleWorldValidationException(e: WorldValidationException): ResponseEntity<ErrorResponse> =
        ResponseEntity.badRequest().body(ErrorResponse(e.message))
}
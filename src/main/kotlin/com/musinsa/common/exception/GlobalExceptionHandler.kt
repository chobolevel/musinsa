package com.musinsa.common.exception

import com.musinsa.common.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(PolicyViolationException::class)
    fun policyViolationExceptionHandler(e: PolicyViolationException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            errorCode = e.errorCode,
            errorMessage = e.message,
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }
}

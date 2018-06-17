package com.zenika.kbooks.exception

import com.zenika.kbooks.feature.error.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime


@RestControllerAdvice
class ExceptionHandlerAdvice {

    @ExceptionHandler(BookNotFoundException::class)
    fun handleException(e: BookNotFoundException): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse().apply {
            errorCode = HttpStatus.NOT_FOUND.value()
            timestamp = LocalDateTime.now()
            message = "Unexpected error (Book)"
            debugMessage = e.localizedMessage

        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse)
    }

    @ExceptionHandler(AuthorNotFoundException::class)
    fun handleException(e: AuthorNotFoundException): ResponseEntity<*> {

        val errorResponse = ErrorResponse().apply {
            errorCode = HttpStatus.NOT_FOUND.value()
            timestamp = LocalDateTime.now()
            message = "Unexpected error (Author)"
            debugMessage = e.localizedMessage
        }


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse)
    }
}
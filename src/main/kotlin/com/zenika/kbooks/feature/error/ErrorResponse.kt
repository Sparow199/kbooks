package com.zenika.kbooks.feature.error

import java.time.LocalDateTime

data class ErrorResponse(
        var errorCode: Int? = null,
        var timestamp: LocalDateTime? = null,
        var message: String? = null,
        var debugMessage: String? = null
)
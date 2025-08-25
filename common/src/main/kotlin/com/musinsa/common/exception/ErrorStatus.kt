package com.musinsa.common.exception

enum class ErrorSeries(val value: Int) {
    INFORMATION(1),
    SUCCESSFUL(2),
    REDIRECTION(3),
    CLIENT_ERROR(4),
    SERVER_ERROR(5)
}

enum class ErrorStatus(val value: Int, val series: ErrorSeries, val message: String) {
    BAD_REQUEST(400, ErrorSeries.CLIENT_ERROR, "Bad Request"),
    UNAUTHORIZED(401, ErrorSeries.CLIENT_ERROR, "Unauthorized"),
    FORBIDDEN(403, ErrorSeries.CLIENT_ERROR, "Forbidden"),
    NOT_FOUND(404, ErrorSeries.CLIENT_ERROR, "Not found"),
    METHOD_NOT_ALLOWED(405, ErrorSeries.CLIENT_ERROR, "Method not allowed"),

    INTERNAL_SERVER_ERROR(500, ErrorSeries.SERVER_ERROR, "Internal server error")
}

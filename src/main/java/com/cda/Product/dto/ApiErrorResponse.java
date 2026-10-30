package com.cda.Product.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        LocalDateTime timestamp,
        String error,
        String message,
        String path
) {
}

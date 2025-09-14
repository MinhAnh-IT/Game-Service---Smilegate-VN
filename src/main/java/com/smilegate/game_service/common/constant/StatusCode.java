package com.smilegate.game_service.common.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum StatusCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Resource not found: %s"),
    INTERNAL_ERROR(500, "Internal Server Error");

    int code;
    String message;

    public String formatMessage(Object... args) {
        return String.format(this.message, args);
    }
}

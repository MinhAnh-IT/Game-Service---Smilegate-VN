package com.smilegate.game_service.exception;

import com.smilegate.game_service.common.constant.StatusCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomException extends RuntimeException {
    StatusCode statusCode;

    public CustomException(StatusCode statusCode, Object... args) {
        super(statusCode.formatMessage(args));
        this.statusCode = statusCode;
    }
}

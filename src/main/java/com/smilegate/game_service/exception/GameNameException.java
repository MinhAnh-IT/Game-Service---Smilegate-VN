package com.smilegate.game_service.exception;

import com.smilegate.game_service.common.constant.StatusCode;

public class GameNameException extends CustomException {
    public GameNameException(StatusCode statusCode, Object... args) {
        super(statusCode, args);
    }
}

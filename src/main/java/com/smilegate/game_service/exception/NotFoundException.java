package com.smilegate.game_service.exception;


import com.smilegate.game_service.common.constant.StatusCode;

public class NotFoundException extends CustomException {
    public NotFoundException(Object... args) {
        super(StatusCode.NOT_FOUND, args);
    }
}

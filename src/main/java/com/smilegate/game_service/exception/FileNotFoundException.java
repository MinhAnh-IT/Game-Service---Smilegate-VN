package com.smilegate.game_service.exception;

import com.smilegate.game_service.common.constant.StatusCode;

public class FileNotFoundException extends CustomException {

    public FileNotFoundException(String filename) {
        super(StatusCode.FILE_NOT_FOUND_ERROR,
                StatusCode.FILE_NOT_FOUND_ERROR.formatMessage(filename));
    }
}

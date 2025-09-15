package com.smilegate.game_service.exception;

import com.smilegate.game_service.common.constant.StatusCode;

public class FileStorageException extends CustomException {

    public FileStorageException(String filename) {
        super(StatusCode.FILE_STORAGE_ERROR,
                StatusCode.FILE_STORAGE_ERROR.formatMessage(filename));
    }

    public FileStorageException(String filename, Throwable cause) {
        super(StatusCode.FILE_STORAGE_ERROR,
                StatusCode.FILE_STORAGE_ERROR.formatMessage(filename));
        initCause(cause);
    }
}

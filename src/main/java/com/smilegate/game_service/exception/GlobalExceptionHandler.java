package com.smilegate.game_service.exception;

import com.smilegate.game_service.common.constant.StatusCode;
import com.smilegate.game_service.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        return ResponseEntity
                .ok(ApiResponse.error(ex.getStatusCode(), ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public  ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException ex){
        return ResponseEntity
                .ok(ApiResponse.error(ex.getStatusCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        return ResponseEntity
                .ok(ApiResponse.error(StatusCode.INTERNAL_ERROR, ex.getMessage()));
    }
}

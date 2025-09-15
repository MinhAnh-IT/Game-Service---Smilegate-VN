package com.smilegate.game_service.exception;

import com.smilegate.game_service.common.constant.StatusCode;
import com.smilegate.game_service.common.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        return ResponseEntity.ok(
                ApiResponse.error(StatusCode.VALIDATION_ERROR, errors)
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        return ResponseEntity.ok(
                ApiResponse.error(StatusCode.VALIDATION_ERROR, errors)
        );
    }


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
        // if ex.getMessage() is null, return INTERNAL_ERROR message
        if (ex.getMessage() == null) {
            return ResponseEntity
                    .ok(ApiResponse.error(StatusCode.INTERNAL_ERROR, StatusCode.INTERNAL_ERROR.getMessage()));
        }
        return ResponseEntity
                .ok(ApiResponse.error(StatusCode.INTERNAL_ERROR, ex.getMessage()));
    }
}

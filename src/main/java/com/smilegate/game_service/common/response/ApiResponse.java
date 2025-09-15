package com.smilegate.game_service.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smilegate.game_service.common.constant.StatusCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    int code;
    String message;
    T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> error(StatusCode statusCode, String customMessage) {
        return new ApiResponse<>(statusCode.getCode(), customMessage, null);
    }
    public static <T> ApiResponse<T> error(StatusCode statusCode, T data) {
        return new ApiResponse<>(statusCode.getCode(), statusCode.getMessage(), data);
    }
}

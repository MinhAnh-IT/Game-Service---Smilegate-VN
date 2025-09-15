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

    // Not Found
    NOT_FOUND(404, "Resource not found: %s"),
    NOT_FOUND_CATEGORY(405, "Category not found: %s"),
    GAME_NAME_NOT_FOUND(406, "Game name not found: %s"),
    GAME_NAME_LANGUAGE_NOT_FOUND(407, "Game name not found with game id and language: %s, %s"),
    LANGUAGE_NOT_FOUND(408, "Language not found: %s"),

    // Server error
    INTERNAL_ERROR(500, "Internal Server Error"),

    // File error
    FILE_STORAGE_ERROR(600, "File storage error: %s"),
    FILE_NOT_FOUND_ERROR(601, "File not found: %s"),

    // GameName validation
    GAME_NAME_DUPLICATE_LANGUAGE(700, "Game already has name for language: %s"),
    GAME_NAME_NO_DEFAULT(701, "No default name specified"),
    GAME_NAME_DEFAULT_EXISTS(701, "Game already has a default name"),
    GAME_NAME_DEFAULT_REQUIRED(702, "Cannot delete the only default name of a game"),
    GAME_NAME_MIN_REQUIRED(703, "At least one game name is required"),


    // Category validation
    EXISTS_CATEGORY_NAME(800, "Category name already exists: %s"),
    // Validation
    VALIDATION_ERROR(1000, "Validation error");

    int code;
    String message;

    public String formatMessage(Object... args) {
        return String.format(this.message, args);
    }
}

package com.smilegate.game_service.validation.validator;

import com.smilegate.game_service.validation.annotation.ValidGameNameValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GameNameValueValidator implements ConstraintValidator<ValidGameNameValue, String> {

    private static final int MAX_LENGTH = 255;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        return value.length() <= MAX_LENGTH;
    }
}

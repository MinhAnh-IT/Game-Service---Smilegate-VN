package com.smilegate.game_service.validation.validator;

import com.smilegate.game_service.validation.annotation.ValidLanguageCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LanguageCodeValidator implements ConstraintValidator<ValidLanguageCode, String> {

    private static final String REGEX = "^[A-Z]{2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches(REGEX);
    }
}

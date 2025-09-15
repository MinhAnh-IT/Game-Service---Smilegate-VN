package com.smilegate.game_service.validation.validator;

import com.smilegate.game_service.application.dto.request.GameNameRequest;
import com.smilegate.game_service.validation.annotation.ValidGameNames;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class GameNamesValidator implements ConstraintValidator<ValidGameNames, List<GameNameRequest>> {

    @Override
    public boolean isValid(List<GameNameRequest> names, ConstraintValidatorContext context) {
        if (names == null || names.isEmpty()) return false;

        long defaultCount = names.stream()
                .filter(GameNameRequest::getDefaultName)
                .count();

        if (defaultCount != 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Exactly one default name must be specified")
                    .addConstraintViolation();
            return false;
        }

        boolean hasDuplicateLanguage = names.stream()
                .map(GameNameRequest::getLanguage)
                .distinct()
                .count() != names.size();

        if (hasDuplicateLanguage) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Duplicate languages are not allowed")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

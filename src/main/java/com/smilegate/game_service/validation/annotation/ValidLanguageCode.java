package com.smilegate.game_service.validation.annotation;

import com.smilegate.game_service.validation.validator.LanguageCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LanguageCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLanguageCode {

    String message() default "Language code must be a 2-letter uppercase code (e.g., EN, KO, JA)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

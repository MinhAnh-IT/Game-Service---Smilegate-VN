package com.smilegate.game_service.validation.annotation;

import com.smilegate.game_service.validation.validator.GameNamesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GameNamesValidator.class)
public @interface ValidGameNames {
    String message() default "Invalid game names";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

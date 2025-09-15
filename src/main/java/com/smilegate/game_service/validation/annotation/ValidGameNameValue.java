package com.smilegate.game_service.validation.annotation;

import com.smilegate.game_service.validation.validator.GameNameValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GameNameValueValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGameNameValue {

    String message() default "Game name value must not be blank and must not exceed 255 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

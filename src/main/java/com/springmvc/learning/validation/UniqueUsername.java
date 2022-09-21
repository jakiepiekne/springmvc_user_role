package com.springmvc.learning.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueUsername {
    String message() default "Enter a unique username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

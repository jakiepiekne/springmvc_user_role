package com.springmvc.learning.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueEmail {
    String message() default "Enter a unique email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

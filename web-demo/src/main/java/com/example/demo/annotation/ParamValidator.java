package com.example.demo.annotation;

import com.example.demo.validator.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {Validator.class})
public @interface ParamValidator {

    String message() default "Parameter error!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String desc();

    String range() default "";

    boolean isRequired();

}

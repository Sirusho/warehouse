package org.example.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = WarehouseDoesNotExistValidator.class)
@Documented
public @interface WarehouseDoesNotExist {

    String message() default "warehouse.already.exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


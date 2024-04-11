package ee.rik.provitoo.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PersonalCodeValidator.class)
public  @interface PersonalCodeValidation {
    public String message() default "Isikukood ei ole valiidne";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}

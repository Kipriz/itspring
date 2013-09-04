package itspring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validation for unique login.
 * Should be applied on class level because we need to know user's id and login to check uniqueness.
 *
 * @author Andrey Volkov
 */
@Constraint(validatedBy = UniqueLoginValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {

    String message() default "{UniqueLogin}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package itspring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Check password strength if it is presented.
 *
 * @author Andrey Volkov
 */
@Constraint(validatedBy = PasswordIfNotEmptyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordIfNotEmpty {
    String message() default "{PasswordIfNotEmpty}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package itspring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrey Volkov
 */
public class PasswordIfNotEmptyValidator implements ConstraintValidator<PasswordIfNotEmpty, String> {

    @Override
    public void initialize(PasswordIfNotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }
        return password.length() >= 4
                && password.matches(".*[A-Z]+.*")
                && password.matches(".*[0-9]+.*");
    }
}

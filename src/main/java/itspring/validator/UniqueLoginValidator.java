package itspring.validator;

import itspring.domain.User;
import itspring.model.UserModel;
import itspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrey Volkov
 */
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, UserModel> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserModel userModel, ConstraintValidatorContext context) {
        if (userModel.getId() != null && userModel.getId() != 0) {
            User user = userRepository.findOne(userModel.getId());
        /* check that user login is the same */
            if (user != null && user.getLogin().equals(userModel.getLogin())) {
                return true;
            }
        }

        User fromDb = userRepository.findByLogin(userModel.getLogin());
        return fromDb == null;
    }
}

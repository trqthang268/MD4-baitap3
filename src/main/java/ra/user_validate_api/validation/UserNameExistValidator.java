package ra.user_validate_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.user_validate_api.repository.UserRepository;

@Component
public class UserNameExistValidator implements ConstraintValidator<UserNameExist, String> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (userRepository == null){
            return true;
        }
        return userRepository.findByUsername(value).isEmpty();
    }
}

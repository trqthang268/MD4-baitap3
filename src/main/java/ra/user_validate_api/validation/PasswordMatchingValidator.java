package ra.user_validate_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatching constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordObj = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordObj = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);
        return Objects.equals(passwordObj, confirmPasswordObj);
    }
}

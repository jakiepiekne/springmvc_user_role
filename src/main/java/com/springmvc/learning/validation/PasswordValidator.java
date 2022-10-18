package com.springmvc.learning.validation;

import com.springmvc.learning.models.UserEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.springmvc.learning.constants.ValidationConstants.PASSWORD_PATTERN;
import static com.springmvc.learning.constants.ValidationConstants.PASSWORD_PATTERN_VIOLATION;
import static com.springmvc.learning.constants.ValidationConstants.PASSWORD_EMPTY_FIELD_VIOLATION;
import static com.springmvc.learning.constants.ValidationConstants.PASSWORD_EQUALITY_VIOLATION;

/**
 * Defines the logic of password validation. Provides password validation when
 * adding a new user either editing existing one. In case of editing existing user,
 * empty password field treated as intention to don't change current password, therefore
 * <code>isValid()</code> in this case returns <font color="orange">true</font> as if password is valid.
 */
public class PasswordValidator implements ConstraintValidator<PasswordValid, UserEntity> {

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String confirmPassword = value.getPasswordConfirm();
        boolean isPasswordBlankOrEmpty = (password.isBlank() || password.isEmpty());
        boolean isConfirmPasswordBlankOrEmpty = (confirmPassword.isBlank() || confirmPassword.isEmpty());
        boolean passwordMatches = password.matches(PASSWORD_PATTERN);
        context.disableDefaultConstraintViolation();
        if (value.getId() == null) {                        // password validation for new users
            if (!isPasswordBlankOrEmpty && !isConfirmPasswordBlankOrEmpty) {
                if (!passwordMatches) {
                    context.buildConstraintViolationWithTemplate(PASSWORD_PATTERN_VIOLATION)
                            .addConstraintViolation();
                    return false;
                } else if (!password.equals(confirmPassword)) {
                    context.buildConstraintViolationWithTemplate(PASSWORD_EQUALITY_VIOLATION)
                            .addConstraintViolation();
                    return false;
                } else {
                    return true;
                }

            } else {
                context.buildConstraintViolationWithTemplate(PASSWORD_EMPTY_FIELD_VIOLATION)
                        .addConstraintViolation();
                return false;
            }
        } else if (value.getId() != null) {           // password validation for edited users
            if (isPasswordBlankOrEmpty && isConfirmPasswordBlankOrEmpty) {   // if user didn't provide a new
                return true;                                                 // password the old one stays actual
            } else {
                boolean passwordsAreEqual = password.equals(confirmPassword);
                if (!passwordMatches) {
                    context.buildConstraintViolationWithTemplate(PASSWORD_PATTERN_VIOLATION)
                            .addConstraintViolation();
                }
                if (!passwordsAreEqual) {
                    context.buildConstraintViolationWithTemplate(PASSWORD_EQUALITY_VIOLATION)
                            .addConstraintViolation();
                }
                return passwordMatches && passwordsAreEqual;
            }
        }
        return false;
    }
}


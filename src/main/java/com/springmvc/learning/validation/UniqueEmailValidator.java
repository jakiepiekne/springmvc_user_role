package com.springmvc.learning.validation;

import com.springmvc.learning.dao.service.UserService;
import com.springmvc.learning.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Checks if email is unique. In case of editing info for an existing user, its email will be treated as
 * unique if not changed.
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String id = request.getParameter("id");
        Optional<UserEntity> userByEmail = userService.findByEmail(value);
        if (id != null) {
            UserEntity userForEditing = userService.findById(Long.valueOf(id)).get();
            if (userForEditing.getEmail().equals(value)) return true;
            else return userByEmail.isEmpty();
        } else return userByEmail.isEmpty();
    }
}

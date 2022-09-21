package com.springmvc.learning.validation;

import com.springmvc.learning.dao.service.UserService;
import com.springmvc.learning.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Checks if username is unique. In case of editing info for an existing user, its username will be treated as
 * unique if not changed.
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String id = request.getParameter("id");
        Optional<UserEntity> userByUsername = userService.findByUsername(value);

        if (id != null) {
            UserEntity userForEditing = userService.findById(Long.valueOf(id)).get();
            boolean usernameBelongsToEditedUser = userForEditing.getUsername().equals(value);
            if (usernameBelongsToEditedUser) return true;
            else return userByUsername.isEmpty();
        } else return userByUsername.isEmpty();
    }
}


package com.springmvc.learning.controllers;

import com.springmvc.learning.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
public class DeleteController {
    @Autowired
    UserService userService;

    /**
     * Deletes user by <code>id</code>, if <code>id</code> of the current authenticated user is the same as
     * <code>id</code> to delete, user will be logged out.
     *
     * @param id defines the user to delete
     */
    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam Long id, Authentication auth, HttpServletRequest httpRequest) {
        userService.findByEmailOrUsername(auth.getName())
                .ifPresent(user -> {
                    try {
                        userService.deleteById(id);
                        if (user.getId().equals(id)) {
                            httpRequest.logout();
                        }
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                });
        return "redirect:/admin";
    }
}

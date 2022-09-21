package com.springmvc.learning.controllers;

import com.springmvc.learning.models.UserEntity;

import javax.validation.Valid;

import com.springmvc.learning.dao.service.RoleService;
import com.springmvc.learning.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddUserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping(value = "/admin/add")
    public String addPage(Model model) {
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("user", new UserEntity());
        return "addUser";
    }

    @PostMapping("/admin/add")
    public String addUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(error -> {
                if (!(error instanceof FieldError)) {
                    model.addAttribute("passwordError", error.getDefaultMessage());
                }
            });
            model.addAttribute("allRoles", roleService.findAll());
            model.addAttribute("user", user);
            return "addUser";
        }
        userService.create(user);
        return "redirect:/admin";
    }
}

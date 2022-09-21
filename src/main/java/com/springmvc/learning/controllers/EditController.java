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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EditController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @GetMapping(value = "/admin/edit")
    public String getEditPage(@RequestParam("id") Long id, Model model) {
        Optional<UserEntity> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("allRoles", roleService.findAll());
            return "editUser";
        }
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(error -> {
                if (!(error instanceof FieldError)) {
                    model.addAttribute("passwordError", error.getDefaultMessage());
                }
            });
            model.addAttribute("user", user);
            model.addAttribute("allRoles", roleService.findAll());
            return "editUser";
        }
        userService.update(user);
        return "redirect:/admin";
    }
}
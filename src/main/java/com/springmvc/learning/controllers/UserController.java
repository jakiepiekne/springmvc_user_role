package com.springmvc.learning.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping(value = "/user")
    public String getUserPage(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        return "userHome";
    }
}

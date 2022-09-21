package com.springmvc.learning.controllers;

import com.springmvc.learning.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "adminHome";
    }
}

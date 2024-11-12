package com.heraj.lab_9.controller;

import com.heraj.lab_9.model.User;
import com.heraj.lab_9.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterationController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registrationForm(Model model) {
        logger.info("inside get register");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        logger.info("inside post register");
        logger.info(user.getSalt());
        logger.info(user.getPassword());
        userService.saveUser(user);
        return "redirect:/login";
    }
}


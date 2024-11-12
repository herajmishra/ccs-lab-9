package com.heraj.lab_9.controller;

import com.heraj.lab_9.model.LoginModel;
import com.heraj.lab_9.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginUtilController.class);

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("inside get register");
        model.addAttribute("login", new LoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute LoginModel login) {
        logger.info("/login form ---- " + login.getPassword() + login.getUsername());
        userService.login(login.getUsername(), login.getPassword());
        return "redirect:/dashboard";
    }
}

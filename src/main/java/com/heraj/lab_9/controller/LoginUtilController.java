package com.heraj.lab_9.controller;

import com.heraj.lab_9.model.LoginModel;
import com.heraj.lab_9.model.SaltResponse;
import com.heraj.lab_9.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginUtilController {

    private static Logger logger = LoggerFactory.getLogger(LoginUtilController.class);

    @Autowired
    UserService userService;

    @GetMapping("/getSalt/{username}")
    public SaltResponse getSalt(@PathVariable String username) {
        logger.info("/getSalt/{username} - " + username);
        return userService.getSalt(username);
    }
}

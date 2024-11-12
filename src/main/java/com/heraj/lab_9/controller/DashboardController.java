package com.heraj.lab_9.controller;

import com.heraj.lab_9.model.CustomUserDetails;
import com.heraj.lab_9.model.dto.UserDto;
import com.heraj.lab_9.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private UserService userService;
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info(authentication.getPrincipal().toString());
        if (authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(authentication.getPrincipal().toString());

            UserDto userDto = userDetails.getUserDto();
            model.addAttribute("username", userDto.getUsername());
            model.addAttribute("firstname", userDto.getFirstname());
            model.addAttribute("lastname", userDto.getLastname());
            model.addAttribute("email", userDto.getEmail());
            logger.info("at dashboard username - " + userDto.getUsername());
        }
        return "dashboard";
    }
}

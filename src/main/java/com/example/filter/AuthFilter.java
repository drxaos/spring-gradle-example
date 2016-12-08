package com.example.filter;

import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AuthFilter {
    @Autowired
    private AuthService authService;

    @ModelAttribute
    public void authRole(Model model) {
        if(authService.hasRole("ROLE_ANONYMOUS")){
            model.addAttribute("__anonymous", true);
        }
        if(authService.hasRole("ROLE_USER")){
            model.addAttribute("__user", true);
        }
        if(authService.hasRole("ROLE_ADMIN")){
            model.addAttribute("__admin", true);
        }
    }
}
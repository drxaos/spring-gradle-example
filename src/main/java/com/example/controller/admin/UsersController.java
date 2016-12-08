package com.example.controller.admin;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/users")
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/users");
        mv.getModel().put("users", userService.listUsers());
        return mv;
    }
}
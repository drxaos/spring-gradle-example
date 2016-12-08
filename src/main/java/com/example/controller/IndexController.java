package com.example.controller;

import com.example.db.Message;
import com.example.db.User;
import com.example.service.AuthService;
import com.example.service.MessageService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public ModelAndView index(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        ModelAndView mv = new ModelAndView();
        if (authService.hasRole(AuthService.ROLE_ANONYMOUS)) {
            mv.getModel().put("name", name);
            mv.setViewName("index");
        } else {
            String username = authService.getUsername();
            mv.getModel().put("name", username);
            mv.setViewName("user/index");
        }
        List<Message> lastMessages = messageService.lastMessages();
        mv.getModel().put("messages", lastMessages);
        return mv;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @RequestMapping("/post")
    public String post(@RequestParam String text) {
        User user = userService.getUser(authService.getUsername());
        messageService.addMessage(user, text);
        return "redirect:/";
    }


}
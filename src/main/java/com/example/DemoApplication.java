package com.example;

import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableScheduling

public class DemoApplication {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void onStart() {
        log.info("Creating test data...");

        List<String> logins = userService.getLogins();
        if (!logins.contains("user")) {
            userService.createUser("user", "12345");
        }
        if (!logins.contains("user1")) {
            userService.createUser("user1", "qwerty");
        }
        if (!logins.contains("user2")) {
            userService.createUser("user2", "asdfg");
        }
        if (!logins.contains("admin")) {
            userService.createAdmin("admin", "12345");
        }
    }
}

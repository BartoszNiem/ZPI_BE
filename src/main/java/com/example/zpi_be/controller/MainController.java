package com.example.zpi_be.controller;

import com.example.zpi_be.model.Login;
import com.example.zpi_be.model.User;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
@CrossOrigin
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return user;
    }

    @PostMapping("/login")
    public User authenticate(@RequestBody Login login) {
        User user = userService.getUserByEmail(login.getEmail());
        return user;
    }
}

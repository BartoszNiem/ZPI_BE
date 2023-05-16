package com.example.zpi_be.controller;

import com.example.zpi_be.model.UserLoginRequest;
import com.example.zpi_be.model.User;
import com.example.zpi_be.model.UserRegisterRequest;
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
    public User save(@RequestBody UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setUsername(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setRole("USER");
        userService.save(user);
        return user;
    }

    @PostMapping("/login")
    public User authenticate(@RequestBody UserLoginRequest login) {
        return userService.getUserByEmail(login.getEmail());
    }
}

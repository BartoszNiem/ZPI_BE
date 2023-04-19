package com.example.zpi_be.controller;

import com.example.zpi_be.model.User;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user) {
        System.out.println("/save");
        return ResponseEntity.ok("Good");
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/users")
    public User getUsers() {
        System.out.println("/users");
        return null;
    }
}

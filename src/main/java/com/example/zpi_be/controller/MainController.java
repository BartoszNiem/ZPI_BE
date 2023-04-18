package com.example.zpi_be.controller;

import com.example.zpi_be.model.User;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api")
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public int save(@RequestBody User user) {
        return 0;
    }
}

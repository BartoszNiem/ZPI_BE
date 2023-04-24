package com.example.zpi_be.controller;


import com.example.zpi_be.model.User;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {

        User dbUser = userService.getUserById(user.getId());
        dbUser.setUsername(user.getUsername());
        dbUser.setDescription(user.getDescription());
        userService.save(dbUser);
        return user;
    }


}

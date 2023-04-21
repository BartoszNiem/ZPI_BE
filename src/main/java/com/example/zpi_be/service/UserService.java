package com.example.zpi_be.service;

import com.example.zpi_be.model.User;
import com.example.zpi_be.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    UserService() {}

    @Autowired
    private UserRepo repo;

    public User save(User user) {
        System.out.println(user.getPassword());
        repo.save(user);
        return user;
    }

    public List<User> getUsers() {
        List<User> users = repo.findAll();
        return users;
    }
}

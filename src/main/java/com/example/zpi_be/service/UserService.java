package com.example.zpi_be.service;

import com.example.zpi_be.model.User;
import com.example.zpi_be.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    UserService() {}

    @Autowired
    private UserRepo repo;

    public User save(User user) { ;
        repo.save(user);
        return user;
    }

    public List<User> getUsers() {
        List<User> users = repo.findAll();
        return users;
    }

    public User getUserByEmail(String email) {
        User user = repo.getByEmail(email);
        return user;
    }

    public User getUserById(long id) {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User with id " + id + " not found.");
        }
    }
}

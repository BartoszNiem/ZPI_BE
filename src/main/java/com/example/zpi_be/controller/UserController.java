package com.example.zpi_be.controller;


import com.example.zpi_be.model.User;
import com.example.zpi_be.service.UserService;
import com.example.zpi_be.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return dbUser;
    }

    @PutMapping("set_avatar/{user_id}")
    public User setAvatarForUserWithId(@RequestParam("image") MultipartFile file, @PathVariable Long user_id) throws IOException {
        User dbUser = userService.getUserById(user_id);
        dbUser.setAvatar(ImageUtility.compressImage(file.getBytes()));
        userService.save(dbUser);
        return dbUser;
    }

    @GetMapping("/avatar/{user_id}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long user_id){
        User dbUser = userService.getUserById(user_id);
        if(dbUser.getAvatar() != null){
            return ResponseEntity.ok()
                    .body(ImageUtility.decompressImage(dbUser.getAvatar()));
        }
        return ResponseEntity.ofNullable(ImageUtility.decompressImage(dbUser.getAvatar()));
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email){
        User dbUser = userService.getUserByEmail(email);
        if(dbUser.getAvatar() != null){
            dbUser.setAvatar(ImageUtility.decompressImage(dbUser.getAvatar()));
        }
        return dbUser;
    }


}

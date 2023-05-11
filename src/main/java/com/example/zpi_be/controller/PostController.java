package com.example.zpi_be.controller;

import com.example.zpi_be.model.Post;
import com.example.zpi_be.model.User;
import com.example.zpi_be.service.PostService;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("")
    List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/newPost")
    Post saveNewPost(@RequestBody Post post){
        postService.savePost(post);
        return post;
    }


}

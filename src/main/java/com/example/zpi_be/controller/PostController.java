package com.example.zpi_be.controller;

import com.example.zpi_be.model.Post;
import com.example.zpi_be.model.PostRequest;
import com.example.zpi_be.model.PostResponse;
import com.example.zpi_be.model.User;
import com.example.zpi_be.service.PostService;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("")
    List<PostResponse> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return posts.stream()
                .map(this::getPostResponse)
                .sorted(Comparator.comparingLong(PostResponse::getId).reversed())
                .collect(Collectors.toList());
    }

    @PostMapping("/newPost")
    Post saveNewPost(@RequestBody PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.getUserByEmail(email);
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setOwnerId(user.getId());
        postService.savePost(post);
        return post;
    }

    @DeleteMapping("/delete/{post_id}")
    Post deletePost(@PathVariable Long post_id) {
        Post dbPost = null;
        try {
            dbPost = postService.findPostById(post_id);
            postService.deletePostById(post_id);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return dbPost;
    }

    private PostResponse getPostResponse(Post post) {
        User user = userService.getUserById(post.getOwnerId());
        PostResponse postResponse = new PostResponse();
        postResponse.setAuthor(user.getUsername());
        postResponse.setContent(post.getContent());
        postResponse.setId(post.getId());
        return postResponse;
    }
}

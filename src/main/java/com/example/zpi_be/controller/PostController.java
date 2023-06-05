package com.example.zpi_be.controller;

import com.example.zpi_be.model.*;
import com.example.zpi_be.service.PostService;
import com.example.zpi_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        ZonedDateTime date= LocalDateTime.now().atZone(ZoneId.of("CET"));
        post.setDate(date);
        postService.savePost(post);
        return post;
    }

    @DeleteMapping("/delete/{post_id}")
    Post deletePost(@PathVariable Long post_id) {
        Post dbPost = null;
        try {
            dbPost = postService.findPostById(post_id);
            postService.deletePostById(post_id);
            if(postService.getPostComments(post_id).size() != 0){
                postService.deleteCommentsByPostId(post_id);
            }
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

    @PostMapping("/add_comment")
//    PostComments addComment(@RequestBody PostComments comment) {
//        ZonedDateTime date = LocalDateTime.now().atZone(ZoneId.of("CET"));
//        comment.setDate(date);
//        postService.addComment(comment);
//        return comment;
//    }
    PostComments addComment(@RequestBody PostCommentRequest comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ZonedDateTime date = LocalDateTime.now().atZone(ZoneId.of("CET"));
        String email = userDetails.getUsername();
        User user = userService.getUserByEmail(email);
        PostComments postComments = new PostComments();
        postComments.setContent(comment.getContent());
        postComments.setPostId(comment.getPostId());
        postComments.setUsername(user.getUsername());
        postComments.setUserId(user.getId());
        postComments.setDate(date);
        postService.addComment(postComments);
        return postComments;
    }
    @GetMapping("/get_comments/{post_id}")
    List<PostComments> getPostComments(@PathVariable Long post_id){
        return postService.getPostComments(post_id);
    }

    @DeleteMapping("delete_post_comment/{comment_id}")
    PostComments deletePostCommentById(@PathVariable Long comment_id){
        PostComments dbComment = postService.getCommentById(comment_id);
        postService.deleteCommentById(comment_id);
        return dbComment;
    }
}

package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "post_comments")
public class PostComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_comment_id")
    private Long id;


    @Column(name="post_id", nullable=false)
    private Long postId;
    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username")
    private String username;

    @Column(name="date_time")
    private ZonedDateTime date;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PostComments() {
    }

    public PostComments(Long id, Long postId, String content, ZonedDateTime date) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long post) {
        this.postId = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}

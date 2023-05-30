package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "image_comment")
public class ImageComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_comment_id")
    private Long id;


    @Column(name="image_id", nullable=false)
    private Long imageId;
    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username")
    private String username;

    @Column(name="date_time")
    private ZonedDateTime date;

    public ImageComment() {
    }

    public ImageComment(Long id, Long imageId, String content, Long userId, String username, ZonedDateTime date) {
        this.id = id;
        this.imageId = imageId;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}

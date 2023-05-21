package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;


    @Column(name = "owner_id")
    private Long ownerId;


    @Column(name = "content")
    private String content;

    @Column(name="date_time")
    private ZonedDateTime date;



    public Post() {
    }

    public Post(Long id, Long ownerId, String content) {
        this.id = id;
        this.ownerId = ownerId;
        this.content = content;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long owner_id) {
        this.ownerId = owner_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

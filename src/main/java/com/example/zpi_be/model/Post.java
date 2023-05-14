package com.example.zpi_be.model;

import jakarta.persistence.*;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;


    @Column(name = "owner_id")
    private long ownerId;


    @Column(name = "content")
    private String content;

    public Post() {
    }

    public Post(long id, long ownerId, String content) {
        this.id = id;
        this.ownerId = ownerId;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long owner_id) {
        this.ownerId = owner_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

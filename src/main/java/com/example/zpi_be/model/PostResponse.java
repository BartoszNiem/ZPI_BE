package com.example.zpi_be.model;

public class PostResponse {
    private Long id;
    private String userName;
    private String author;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return author;
    }

    public void setContent(String content) {
        this.author = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

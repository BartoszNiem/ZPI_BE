package com.example.zpi_be.model;

public class PostResponse {
    private Long id;
    private String author;
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String userName) {
        this.author = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

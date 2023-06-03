package com.example.zpi_be.model;

public class ImageCommentRequest {
    Long imageId;
    String content;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long postId) {
        this.imageId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

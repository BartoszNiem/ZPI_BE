package com.example.zpi_be.model;

import jakarta.persistence.Column;

import java.time.ZonedDateTime;

public class ImageResponse {


    private Long id;
    private Long ownerId;
    private byte[] imageData;
    private String name;
    private Integer category;
    private String description;
    private ZonedDateTime date;
    public ImageResponse() {
    }

    public ImageResponse(Long id, Long ownerId, byte[] imageData, String name, Integer category, String description, ZonedDateTime date) {
        this.id = id;
        this.ownerId = ownerId;
        this.imageData = imageData;
        this.name = name;
        this.category = category;
        this.description = description;
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

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}

package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "image_rating")
public class ImageRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_rating_id")
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "rating")
    private Integer rating;

    public ImageRating() {
    }

    public ImageRating(Long id, Long ownerId, Long imageId, Integer currentRating) {
        this.id = id;
        this.ownerId = ownerId;
        this.imageId = imageId;
        this.rating = currentRating;
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer currentRating) {
        this.rating = currentRating;
    }
}

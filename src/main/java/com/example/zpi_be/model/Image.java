package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "pictures")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "name")
    private String name;

    @Column(name="username")
    private String username;

    @Column(name = "category")
    private Integer category;

    @Column(name = "description")
    private String description;

    @Column(name="date_time")
    private ZonedDateTime date;

    @Column(name="current_rating", columnDefinition="DOUBLE(10,2)" )
    private Double currentRating;
    @Column(name = "number_of_ratings")
    private Integer numberOfRatings;

    public Image() {
    }

    public Double getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Double currentRating) {
        this.currentRating = currentRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image(Long id, Long ownerId, String filePath, Integer category, String description, ZonedDateTime date) {
        this.id = id;
        this.ownerId = ownerId;
        this.filePath = filePath;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

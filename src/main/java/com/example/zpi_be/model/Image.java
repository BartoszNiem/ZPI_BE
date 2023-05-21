package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Set;

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

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name="date_time")
    private ZonedDateTime date;
    public Image() {
    }

    public Image(Long id, Long ownerId, String filePath, String category, String description, ZonedDateTime date) {
        this.id = id;
        this.ownerId = ownerId;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

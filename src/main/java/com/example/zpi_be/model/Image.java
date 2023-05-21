package com.example.zpi_be.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "pictures")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "categories")
    private String categories;


}

package com.example.zpi_be.repository;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.model.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    @Query("SELECT image FROM Image image WHERE image.name = ?1")
    Image getImageByName(String fileName);
}

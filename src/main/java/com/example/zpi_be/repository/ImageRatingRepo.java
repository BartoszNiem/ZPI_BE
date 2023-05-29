package com.example.zpi_be.repository;

import com.example.zpi_be.model.Image;
import com.example.zpi_be.model.ImageRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ImageRatingRepo extends JpaRepository<ImageRating, Long> {
    @Query("SELECT imageRating FROM ImageRating imageRating WHERE imageRating.ownerId = ?1 AND imageRating.imageId = ?2")
    ImageRating getImageRatingByOwnerIdAndImageId(Long ownerId, Long imageId);
}

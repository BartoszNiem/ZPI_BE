package com.example.zpi_be.repository;

import com.example.zpi_be.model.ImageComment;
import com.example.zpi_be.model.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ImageCommentRepo extends JpaRepository<ImageComment, Long> {
    @Query("SELECT images FROM ImageComment images WHERE images.imageId = ?1")
    List<ImageComment> getCommentsByImageId(Long imageId);
}

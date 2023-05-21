package com.example.zpi_be.repository;

import com.example.zpi_be.model.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface PostCommentsRepo extends JpaRepository<PostComments, Long> {
    @Query("SELECT posts FROM PostComments posts WHERE posts.postId = ?1")
    List<PostComments> getCommentsByPostId(Long postId);
}

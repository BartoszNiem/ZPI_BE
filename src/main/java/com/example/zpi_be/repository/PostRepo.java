package com.example.zpi_be.repository;


import com.example.zpi_be.model.Post;
import com.example.zpi_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @Query("SELECT posts FROM Post posts WHERE posts.owner_id = ?1")
    List<Post> getPostsByOwnerId(Long owner_id);
}

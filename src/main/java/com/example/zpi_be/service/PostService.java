package com.example.zpi_be.service;

import com.example.zpi_be.model.Post;
import com.example.zpi_be.model.PostComments;
import com.example.zpi_be.repository.PostCommentsRepo;
import com.example.zpi_be.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    PostService() {}

    @Autowired
    PostRepo postRepo;

    @Autowired
    PostCommentsRepo postCommentsRepo;

    public Post savePost(Post post){
        postRepo.save(post);
        return post;
    }

    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }

    public Post findPostById(Long id){
        Optional<Post> post = postRepo.findById(id);
        if(post.isPresent()){
            return post.get();
        }
        else{
            throw new RuntimeException("There is no post with that id: " + id);
        }
    }

    public Post deletePostById(Long id){
        try{
            Post deletedPost = findPostById(id);
            postRepo.delete(deletedPost);
            return deletedPost;
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }
    }

    public PostComments addComment(PostComments comment){
       postCommentsRepo.save(comment);
       return comment;
    }

    public List<PostComments> getPostComments(Long postId){
        return postCommentsRepo.getCommentsByPostId(postId);
    }


    public PostComments getCommentById(Long commentId) {
        return postCommentsRepo.findById(commentId).get();
    }

    public void deleteCommentById(Long commentId) {
        postCommentsRepo.deleteById(commentId);
    }

    public void deleteCommentsByPostId(Long postId) {
        postCommentsRepo.deleteCommentByPostId(postId);
    }
}



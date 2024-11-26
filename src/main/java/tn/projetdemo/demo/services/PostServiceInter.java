package tn.projetdemo.demo.services;

import tn.projetdemo.demo.entities.Post;

import java.util.List;

public interface PostServiceInter {


    Post createPost(Long userId, Post post);

    List<Post> getAllPosts();

    Post getPostById(Long postId);

    Post updatePost(Long postId, Post updatedPost);

    void deletePost(Long postId);

    List<Post> getPostsByUserId(Long userId);
}

package tn.projetdemo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.projetdemo.demo.entities.Comment;
import tn.projetdemo.demo.entities.Post;
import tn.projetdemo.demo.services.CommentServiceImpl;
import tn.projetdemo.demo.services.PostServiceInter;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    // Create Comment for a Post
    @PostMapping("/user/{userId}/post/{postId}")
    public Comment createComment(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @RequestBody Comment comment) {
        return commentService.createComment(userId, postId, comment);


    }


    // Get all comments
    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // Get a comment by ID
    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    // Update a comment
    @PutMapping("/{commentId}")
    public Comment updateComment(
            @PathVariable Long commentId,
            @RequestBody Comment updatedComment) {
        return commentService.updateComment(commentId, updatedComment);
    }

    // Delete a comment by ID
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }



    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }




}








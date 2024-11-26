package tn.projetdemo.demo.services;

import tn.projetdemo.demo.entities.Comment;
import tn.projetdemo.demo.entities.Post;

import java.util.List;

public interface CommentServiceInter {



    Comment createComment(Long userId, Long postId, Comment comment);

    List<Comment> getAllComments();

    Comment getCommentById(Long commentId);

    Comment updateComment(Long commentId, Comment updatedComment);

    void deleteComment(Long commentId);

    List<Comment> getCommentsByPostId(Long postId);

}

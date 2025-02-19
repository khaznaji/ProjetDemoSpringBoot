package tn.projetdemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projetdemo.demo.entities.Comment;
import tn.projetdemo.demo.entities.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {



    List<Comment> findByPostPostId(Long postId);



}

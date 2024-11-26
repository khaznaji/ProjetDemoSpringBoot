package tn.projetdemo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.projetdemo.demo.entities.Comment;
import tn.projetdemo.demo.entities.Post;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.repository.CommentRepository;
import tn.projetdemo.demo.repository.PostRepository;
import tn.projetdemo.demo.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentServiceInter {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Comment createComment(Long userId, Long postId, Comment comment) {
		User user = userRepository.getUserById(userId);
		if (user != null) {
			Optional<Post> optionalPost = postRepository.findById(postId);
			if (optionalPost.isPresent()) {
				Post post = optionalPost.get();
				comment.setPost(post);
				comment.setCreated(new Date());
				return commentRepository.save(comment);
			} else {
				throw new IllegalArgumentException("Poste avec l'ID " + postId + " introuvable");
			}
		} else {
			throw new IllegalArgumentException("Utilisateur avec l'ID " + userId + " introuvable");
		}
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public Comment getCommentById(Long commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException("Commentaire avec l'ID " + commentId + " introuvable"));
	}

	@Override
	public Comment updateComment(Long commentId, Comment updatedComment) {
		Comment existingComment = getCommentById(commentId);
		existingComment.setBody(updatedComment.getBody());
		return commentRepository.save(existingComment);
	}

	@Override
	public void deleteComment(Long commentId) {
		Comment comment = getCommentById(commentId);
		commentRepository.delete(comment);
	}

	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		return commentRepository.findByPostPostId(postId);
	}
}

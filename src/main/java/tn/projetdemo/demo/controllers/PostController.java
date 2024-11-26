package tn.projetdemo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.projetdemo.demo.entities.Post;
import tn.projetdemo.demo.entities.User;
import tn.projetdemo.demo.services.PostServiceImpl;
import tn.projetdemo.demo.services.PostServiceInter;
import tn.projetdemo.demo.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PostController {


	@Autowired
	private PostServiceInter postServiceInter;


	// Create
	@PostMapping("add/{userId}")
	public Post createPost(@PathVariable Long userId, @RequestBody Post post) {

		return postServiceInter.createPost(userId, post);
	}

	// Read
	@GetMapping("/all")
	public List<Post> getAllPosts() {
		return postServiceInter.getAllPosts();
	}







	@GetMapping("/{postId}")
	public Post getPostById(@PathVariable Long postId) {
		return postServiceInter.getPostById(postId);
	}



	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
		return postServiceInter.updatePost(postId, updatedPost);
	}







	// Delete
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postServiceInter.deletePost(postId);
	}



	@GetMapping("/user/{userId}")
	public List<Post> getPostsByUserId(@PathVariable Long userId) {
		return postServiceInter.getPostsByUserId(userId);
	}

}


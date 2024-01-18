package co.mateusbello.rest.webservices.restfulwebservices.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.mateusbello.rest.webservices.restfulwebservices.bean.Post;
import co.mateusbello.rest.webservices.restfulwebservices.bean.User;
import co.mateusbello.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import co.mateusbello.rest.webservices.restfulwebservices.service.PostService;
import co.mateusbello.rest.webservices.restfulwebservices.service.UserService;
import jakarta.validation.Valid;

@RestController
public class PostController {
	
	private UserService userService;
	private PostService postService;
	
	public PostController(UserService userService, PostService postService) {
		this.userService = userService;
		this.postService = postService;
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
		Optional<User> user = userService.findByIdFromDb(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User " + id +" not found");
		}
		post.setUser(user.get());
		Post postSaved = postService.save(post);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(postSaved.getId()).toUri()).build();
	}
	
}
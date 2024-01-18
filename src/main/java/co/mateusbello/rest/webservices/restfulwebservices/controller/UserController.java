package co.mateusbello.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.mateusbello.rest.webservices.restfulwebservices.bean.Post;
import co.mateusbello.rest.webservices.restfulwebservices.bean.User;
import co.mateusbello.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import co.mateusbello.rest.webservices.restfulwebservices.service.UserService;
import jakarta.validation.Valid;

@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable Integer id) {
		User user = userService.findById(id);
		if (user == null) {
			throw new UserNotFoundException("User " + id +" not found");
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri()).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
	}
	
	@GetMapping("/jpa/users")
	public List<User> getAllUsersFromDb() {
		
		return userService.findAllDbUsers();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> getUserByIdFromDb(@PathVariable Integer id) {
		Optional<User> user = userService.findByIdFromDb(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User " + id +" not found");
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsersFromDb());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createDbUser(@Valid @RequestBody User user) {
		User savedUser = userService.saveToDb(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri()).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserFromDb(@PathVariable Integer id) {
		userService.deleteByIdFromDb(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retreivePOstForUser(@PathVariable Integer id) {
		Optional<User> user = userService.findByIdFromDb(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User " + id +" not found");
		}
		return user.get().getPosts();
	}
}
package co.mateusbello.rest.webservices.restfulwebservices.service;

import org.springframework.stereotype.Component;

import co.mateusbello.rest.webservices.restfulwebservices.bean.Post;
import co.mateusbello.rest.webservices.restfulwebservices.repository.PostRepository;

@Component
public class PostService {
	
	private PostRepository postRepository;

	
	public PostService(PostRepository repository) {
		this.postRepository = repository;
	}
	
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	
}

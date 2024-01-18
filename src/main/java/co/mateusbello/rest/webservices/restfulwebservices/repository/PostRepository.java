package co.mateusbello.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.mateusbello.rest.webservices.restfulwebservices.bean.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}

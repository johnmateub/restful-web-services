package co.mateusbello.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.mateusbello.rest.webservices.restfulwebservices.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

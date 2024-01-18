package co.mateusbello.rest.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import co.mateusbello.rest.webservices.restfulwebservices.bean.User;
import co.mateusbello.rest.webservices.restfulwebservices.repository.UserRepository;

@Component
public class UserService {
	
	private UserRepository userRepository;

	private static Integer usersCount = 3;
	private static List<User> users = new ArrayList<>();
	static {
 		users.add(new User(1, "Ronaldinho", LocalDate.now().minusYears(40)));
 		users.add(new User(2, "Zidane", LocalDate.now().minusYears(50)));
 		users.add(new User(3, "Asprilla", LocalDate.now().minusYears(45)));
	}
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findById(Integer id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public void deleteById(Integer id) {
		users.removeIf(user -> user.getId() == id);
	}
	
	public List<User> findAllDbUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> findByIdFromDb(Integer id) {
		return userRepository.findById(id);
	}
	
	public User saveToDb(User user) {
		return userRepository.save(user);
	}
	
	public void deleteByIdFromDb(Integer id) {
		userRepository.deleteById(id);
	}
	
	
}

package co.mateusbello.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -3723409650591570369L;

	public UserNotFoundException(String message) {
		super(message);
	}

}

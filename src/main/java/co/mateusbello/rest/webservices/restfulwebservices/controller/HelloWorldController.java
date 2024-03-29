package co.mateusbello.rest.webservices.restfulwebservices.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.rest.webservices.restfulwebservices.bean.HelloWorldBean;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("HEllo");
	}
	
	@GetMapping("hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("HEllo" + name);
	}
	
	@GetMapping("hello-world-i18n")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, "Default", LocaleContextHolder.getLocale());
	}

}

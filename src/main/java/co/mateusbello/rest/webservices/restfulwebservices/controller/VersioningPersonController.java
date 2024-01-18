package co.mateusbello.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.rest.webservices.restfulwebservices.bean.Name;
import co.mateusbello.rest.webservices.restfulwebservices.bean.PersonV1;
import co.mateusbello.rest.webservices.restfulwebservices.bean.PersonV2;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getPersonv1() {
		return new PersonV1("Ronaldinho Gaucho");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonv2() {
		return new PersonV2(new Name("Ronaldiho", " Gaucho"));
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getPersonv1RequestParam() {
		return new PersonV1("Ronaldinho Gaucho");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getPersonv2RequestParam() {
		return new PersonV2(new Name("Ronaldiho", " Gaucho"));
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonv1RequestHeader() {
		return new PersonV1("Ronaldinho Gaucho");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonv2RequestHeader() {
		return new PersonV2(new Name("Ronaldiho", " Gaucho"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vdn.company.app-v1+json")
	public PersonV1 getPersonv1Accept() {
		return new PersonV1("Ronaldinho Gaucho");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vdn.company.app-v2+json")
	public PersonV2 getPersonv2Accept() {
		return new PersonV2(new Name("Ronaldiho", " Gaucho"));
	}
}

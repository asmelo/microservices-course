package com.asmelo.rest.webservices.restfulwebservicesv2.versioning;

import org.springframework.web.bind.annotation.GetMapping;

public class VersioningController {	
	
	//URI Versioning (Usado pelo Twitter)
	//Muda apenas a URI
	@GetMapping("v1/person")
	public Person1 person1() {
		return new Person1("Bob Charlie");
	}
	
	@GetMapping("v2/person")
	public Person2 person2() {
		return new Person2(new Name("Bob", "Charlie"));
	}
	
	
	
	//Request Parameter versioning (Usado pela Amazon)
	//Acrescenta um parâmetro na URI 
	//Ex.: http://localhot:8080/person/param?version=1
	@GetMapping(value = "person/param", params = "version=1")
	public Person1 paramV1() {
		return new Person1("Bob Charlie");
	}
	
	@GetMapping(value = "person/param", params = "version=2")
	public Person2 paramV2() {
		return new Person2(new Name("Bob", "Charlie"));
	}
	
	
	
	//Headers versioning (Usando pela Microsoft)
	//Um parâmetro no header definirá a versão 
	//Ex.: No Header do Postman coloca key:X-API-VERSION value:1
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public Person1 headerV1() {
		return new Person1("Bob Charlie");
	}
	
	@GetMapping(value = "person/header", headers = "X-API-VERSION=2")
	public Person2 headerV2() {
		return new Person2(new Name("Bob", "Charlie"));
	}
	
	
	
	//Media type versioning (Usado pela GitHub)
	//Definido pelo atributo Accept do header
	//Ex.: No Header do Postman coloca key:Accept value:application/vnd.company.app-v1+json
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public Person1 produceV1() {
		return new Person1("Bob Charlie");
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public Person2 produceV2() {
		return new Person2(new Name("Bob", "Charlie"));
	}

}

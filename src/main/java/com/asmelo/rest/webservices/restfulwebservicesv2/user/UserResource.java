package com.asmelo.rest.webservices.restfulwebservicesv2.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asmelo.rest.webservices.restfulwebservicesv2.post.Post;
import com.asmelo.rest.webservices.restfulwebservicesv2.post.PostDaoService;

@RestController
public class UserResource {

	@Autowired
	UserDaoService service;
	
	@Autowired
	PostDaoService postDaoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable final int id) {
		User user = service.findOne(id);
		if (user == null) 
			throw new UserNotFoundException("id: " + id);
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {				
		if (user.getName() == null)
			throw new UserBadRequestException("O nome do usuário é obrigatório");
		
		User userSaved = service.save(user);		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void createUser(@PathVariable int id) {							
		User user = service.deleteById(id);
		
		if (user == null)
			throw new UserNotFoundException("id: " + id);			
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable final int id) {
		return postDaoService.findAll(id);
	}
	
	@GetMapping("/users/{id}/posts/{idPost}")
	public Post retrievePost(@PathVariable final int idPost) {
		Post post = postDaoService.findOne(idPost);
		if (post == null) 
			throw new PostNotFoundException("idPost: " + idPost);
		
		return post;
	}
	
	@PostMapping("users/{id}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post) {
		if (post.getContent() == null || post.getIdUser() == null)
			throw new PostBadRequestException("O conteúdo e o usuário são obrigatórios");
		
		Post postSaved = postDaoService.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}

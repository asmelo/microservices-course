package com.asmelo.rest.webservices.restfulwebservicesv2.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.asmelo.rest.webservices.restfulwebservicesv2.user.User;

@Component
public class PostDaoService {

private static List<Post> posts= new ArrayList<>();
	
	private static int postsCount = 5;
	
	static {
		posts.add(new Post(1, "Olá tudo bem?", 1));
		posts.add(new Post(2, "Você poderia me ajudar?", 1));
		posts.add(new Post(3, "Quando possível me responda?", 1));
		posts.add(new Post(4, "Olá! Tudo bem!?", 2));
		posts.add(new Post(5, "Sim, posso te ajudar?", 2));
	}
	
	public List<Post> findAll(int idUser) {		
		return posts.stream().filter(p -> {
			return p.getIdUser() == idUser;
		}).collect(Collectors.toList());
	}
	
	public Post save(Post post) {
		if (post.getId()==null) {
			post.setId(++postsCount);
		}
		posts.add(post);	
		return post;
	}
	
	public Post findOne(int id) {
		for (Post post: posts) {
			if (post.getId() == id) {
				return post;
			}
		}
		return null;
	}

	
}

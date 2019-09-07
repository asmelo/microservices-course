package com.asmelo.rest.webservices.restfulwebservicesv2.post;

public class Post {
	
	private Integer id;
	private String content;
	private Integer idUser;
	
	
	public Post() {
		super();
	}


	public Post(Integer id, String content, Integer idUser) {
		super();
		this.id = id;
		this.content = content;
		this.idUser = idUser;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getIdUser() {
		return idUser;
	}


	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
		

}

package fr.m2i.demo.front.model;

import org.hibernate.validator.constraints.Length;

public class MUser {

	private Long id;
	@Length(min = 3)
	private String username;
	@Length(min = 3)
	private String password;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public MUser() {
		// TODO Auto-generated constructor stub
	}

	public MUser(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}

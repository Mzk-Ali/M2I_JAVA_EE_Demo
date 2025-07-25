package fr.m2i.demo.api.controllers.payload.requests;

public class PutUserRequest {

	private Long id;
	private String username;
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

	public PutUserRequest() {
		// TODO Auto-generated constructor stub
	}

	public PutUserRequest(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
}

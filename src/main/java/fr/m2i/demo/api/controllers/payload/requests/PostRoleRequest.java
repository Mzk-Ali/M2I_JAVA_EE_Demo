package fr.m2i.demo.api.controllers.payload.requests;

public class PostRoleRequest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PostRoleRequest() {

	}

	public PostRoleRequest(String name) {
		super();
		this.name = name;
	}
}

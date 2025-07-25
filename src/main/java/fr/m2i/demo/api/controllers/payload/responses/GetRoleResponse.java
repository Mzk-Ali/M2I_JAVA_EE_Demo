package fr.m2i.demo.api.controllers.payload.responses;

public class GetRoleResponse {
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public GetRoleResponse() {

	}
	
	public GetRoleResponse(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}

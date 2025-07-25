package fr.m2i.demo.api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String name;
	

    @OneToMany(mappedBy = "role")
    private List<EUser> users = new ArrayList<>();
	
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
	
	public List<EUser> getUsers() {
		return users;
	}
	public void setUsers(List<EUser> users) {
		this.users = users;
	}
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}

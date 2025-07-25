package fr.m2i.demo.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.m2i.demo.api.controllers.payload.requests.PostRoleRequest;
import fr.m2i.demo.api.controllers.payload.responses.GetRoleResponse;
import fr.m2i.demo.api.services.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	private RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostMapping
	public GetRoleResponse addRole(@RequestBody PostRoleRequest role) {
		return this.roleService.save(role);
	}
	
	@GetMapping
	public List<GetRoleResponse> getRoles(){
		return roleService.getAll();
	}
}

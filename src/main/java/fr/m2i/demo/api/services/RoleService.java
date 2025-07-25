package fr.m2i.demo.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.m2i.demo.api.controllers.payload.requests.PostRoleRequest;
import fr.m2i.demo.api.controllers.payload.responses.GetRoleResponse;
import fr.m2i.demo.api.entities.Role;
import fr.m2i.demo.api.repositories.RoleRepository;

@Service
public class RoleService {
	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<GetRoleResponse> getAll() {
		List<Role> roles = this.roleRepository.findAll();
		List<GetRoleResponse> dtos = new ArrayList<GetRoleResponse>();
		roles.forEach(entity -> {
			dtos.add(toDto(entity));
		});
		return dtos;
	}
	
	public GetRoleResponse save(PostRoleRequest role) {
		Role entityRole = new Role();
		entityRole.setName(role.getName());
		return save(entityRole);
	}
	
	private GetRoleResponse save(Role entity) {
		Role newEntity = this.roleRepository.save(entity);
		return toDto(newEntity);
	}
	
	private GetRoleResponse toDto(Role entity) {
		GetRoleResponse dto = new GetRoleResponse(entity.getId(), entity.getName());
		return dto;
	}
}

package fr.m2i.demo.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.m2i.demo.api.controllers.payload.requests.PostUserRequest;
import fr.m2i.demo.api.controllers.payload.requests.PutUserRequest;
import fr.m2i.demo.api.controllers.payload.responses.GetRoleResponse;
import fr.m2i.demo.api.controllers.payload.responses.GetUserResponse;
import fr.m2i.demo.api.entities.EUser;
import fr.m2i.demo.api.entities.Role;
import fr.m2i.demo.api.repositories.EUserRepository;
import fr.m2i.demo.api.repositories.RoleRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	private EUserRepository userRepository;
	private RoleService roleService;
	private PasswordEncoder passwordEncoder;

	public UserService(
			EUserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<GetUserResponse> getAll() {
		List<EUser> users = this.userRepository.findAll();
		List<GetUserResponse> dtos = new ArrayList<GetUserResponse>();
		users.forEach(entity -> {
			dtos.add(toDto(entity));
		});
		return dtos;
	}

	public Optional<GetUserResponse> getUserById(Long id) {
		Optional<EUser> result = this.userRepository.findById(id);
		if (result.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(toDto(result.get()));
		}
	}

	public Optional<GetUserResponse> getUserByUsername(String username) {
		Optional<EUser> result = this.userRepository.findByUsername(username);
		if (result.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(toDto(result.get()));
		}
	}

	public GetUserResponse save(PostUserRequest user) {
		EUser entityUser = new EUser();
		entityUser.setUsername(user.getUsername());
		String encodedPassword = 
				passwordEncoder.encode(user.getPassword());
		entityUser.setPassword(encodedPassword);
		
		
//		List<GetRoleResponse> roles = this.roleService.getAll();
//		
//		Optional<GetRoleResponse> role = roles.stream()
//			    .filter(r -> "Role_User".equals(r.getName()))
//			    .findFirst();
//		
//        GetRoleResponse entityRole = role.get();
//        
//        entityUser.setRole(entityRole);

		return save(entityUser);
	}

	public GetUserResponse save(PutUserRequest user) {
		EUser entityUser = new EUser();
		entityUser.setId(user.getId());
		entityUser.setUsername(user.getUsername());
		String encodedPassword = 
				passwordEncoder.encode(user.getPassword());
		entityUser.setPassword(encodedPassword);

		return save(entityUser);
	}

	private GetUserResponse save(EUser entity) {
		EUser newEntity = this.userRepository.save(entity);
		return toDto(newEntity);
	}

	public void deleteAll() {
		this.userRepository.deleteAll();
	}

	public void deleteByUsername(String username) {
		this.userRepository.deleteByUsername(username);
	}

	private GetUserResponse toDto(EUser entity) {
		GetUserResponse dto = new GetUserResponse(entity.getId(), entity.getUsername());
		return dto;
	}
}

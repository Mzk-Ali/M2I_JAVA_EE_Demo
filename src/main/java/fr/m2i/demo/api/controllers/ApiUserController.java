package fr.m2i.demo.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.m2i.demo.api.controllers.payload.requests.PostUserRequest;
import fr.m2i.demo.api.controllers.payload.requests.PutUserRequest;
import fr.m2i.demo.api.controllers.payload.responses.GetUserResponse;
import fr.m2i.demo.api.services.UserService;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	private UserService userService;

	public ApiUserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<GetUserResponse> getUsers() {
		return userService.getAll();
	}

	@PostMapping
	public GetUserResponse addUser(@RequestBody PostUserRequest user) {
		return this.userService.save(user);
	}

	@GetMapping("/{username}")
	public ResponseEntity<GetUserResponse> getByUsername(@PathVariable(name = "username") String username) {
		Optional<GetUserResponse> result = this.userService.getUserByUsername(username);
		if (result.isPresent()) {
			return new ResponseEntity<GetUserResponse>(result.get(), HttpStatus.OK);
		}
		return new ResponseEntity<GetUserResponse>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		this.userService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteByUsername(@PathVariable(name = "username") String username) {
		this.userService.deleteByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<GetUserResponse> update(@RequestBody PutUserRequest user) {
		Optional<GetUserResponse> result = this.userService.getUserById(user.getId());
		if(result.isPresent()) {
			GetUserResponse updatedUser = this.userService.save(user);
			return new ResponseEntity<GetUserResponse>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<GetUserResponse>(HttpStatus.NOT_FOUND);		
	}
//	
//	@PatchMapping
//	public ResponseEntity<EUser> partialUpdate(@RequestBody User user) {
//		Optional<EUser> result = this.userService.getUserById(user.getId());
//		if (result.isPresent()) {
//			EUser existingUser = result.get();
//			if (user.getUsername() == null) {
//				user.setUsername(existingUser.getUsername());
//			}
//			if (user.getPassword() == null) {
//				user.setPassword(existingUser.getPassword());
//			}
//			EUser updatedUser = this.userService.save(user);
//			return new ResponseEntity<EUser>(updatedUser, HttpStatus.OK);
//		}
//		return new ResponseEntity<EUser>(HttpStatus.NOT_FOUND);
//	}

}
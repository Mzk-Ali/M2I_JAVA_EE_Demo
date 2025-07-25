package fr.m2i.demo.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.m2i.demo.api.controllers.payload.requests.LoginRequest;
import fr.m2i.demo.api.services.JwtService;

@RestController
public class AuthController {

	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			User authenticatedUser = (User) authenticate.getPrincipal();

			String token = this.jwtService.generateToken(authenticatedUser.getUsername());
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (BadCredentialsException ex) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
	}	
	
}

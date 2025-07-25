package fr.m2i.demo.front.repositories;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.m2i.demo.front.model.MUser;
import jakarta.validation.Valid;

@Component
public class UserProxy {
	
	private final String baseApiUrl = "http://localhost:9000/api";
	private RestTemplate restTemplate = new RestTemplate();
	private String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidGVzdCIsImV4cCI6MTc1MjkyNTkwOSwiaWF0IjoxNzUyODM5NTA5fQ.K0sjqhi8mHqZ5iAAgvB5_UthY3uc_7rqp42xIRV9-YY";
	
	private HttpHeaders createTokenHeader(String token) {
		String authHeader = "Bearer " + token;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", authHeader);
		return httpHeaders;
	}	
	
	public List<MUser> getAllUsers() {		
		HttpEntity request = new HttpEntity(createTokenHeader(token));
		
		ResponseEntity<List<MUser>> response = this.restTemplate.exchange(
				this.baseApiUrl + "/user",
				HttpMethod.GET,
				request,
				new ParameterizedTypeReference<List<MUser>>() {}
				);
		
		return response.getBody();
	}
	
	public MUser getUserByUsername(String username) {		
		HttpEntity request = new HttpEntity(createTokenHeader(token));
		
		ResponseEntity<MUser> response = this.restTemplate.exchange(
				this.baseApiUrl + "/user/" + username,
				HttpMethod.GET,
				request,
				MUser.class);
		
		return response.getBody();
	}


	public MUser save(@Valid MUser user) {
		HttpEntity request = new HttpEntity(user, createTokenHeader(token));
		
		ResponseEntity<MUser> response = this.restTemplate.exchange(
				this.baseApiUrl + "/user",
				HttpMethod.POST,
				request,
				MUser.class);
		
		return response.getBody();		
	}
	

	public String login(MUser user) {
		HttpEntity request = new HttpEntity(user, createTokenHeader(token));
		
		ResponseEntity<String> response = this.restTemplate.exchange(
				this.baseApiUrl + "/login",
				HttpMethod.POST,
				request,
				String.class);
		
		return response.getBody();
	}

}

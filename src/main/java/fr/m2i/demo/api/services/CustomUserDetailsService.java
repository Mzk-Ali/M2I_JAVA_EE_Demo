package fr.m2i.demo.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.m2i.demo.api.entities.EUser;
import fr.m2i.demo.api.repositories.EUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private EUserRepository userRepository;
	
	public CustomUserDetailsService(EUserRepository userRepository) {
		this.userRepository = userRepository;
	}	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<EUser> result = this.userRepository.findByUsername(username);
		if(result.isEmpty()) {
			throw new UsernameNotFoundException(username + " not found");
		}
		EUser entity = result.get();
		
		UserDetails user = new User(
				entity.getUsername(), 
				entity.getPassword(), 
				getDefaultGrantedsAuthorities());		
		
		return user;
	}
	
	private List<GrantedAuthority> getDefaultGrantedsAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}

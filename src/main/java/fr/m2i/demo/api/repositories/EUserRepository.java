package fr.m2i.demo.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.demo.api.entities.EUser;

@Repository
public interface EUserRepository extends JpaRepository<EUser, Long> {

	public Optional<EUser> findByUsername(String username);

	public void deleteByUsername(String username);
	
}

package fr.m2i.demo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.demo.api.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
}

package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Userlogin;

@Repository
public interface LoginRepository extends JpaRepository<Userlogin, Integer>{

	Optional<Userlogin> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<Userlogin> findByUsernameAndEmail(String username, String email);

}

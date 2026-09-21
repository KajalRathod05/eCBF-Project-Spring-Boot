package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Customertemp;

@Repository
public interface CustomerTempRepository extends JpaRepository<Customertemp, Long>{
	
	boolean existsByEmail(String email);

	boolean existsByMobileno(String mobileno);


}

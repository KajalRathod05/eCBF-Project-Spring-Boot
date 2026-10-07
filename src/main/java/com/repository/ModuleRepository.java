package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.ModuleMST;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleMST, Integer>{

}

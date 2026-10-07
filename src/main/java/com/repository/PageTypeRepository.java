package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.PageTypeMST;

@Repository
public interface PageTypeRepository extends JpaRepository<PageTypeMST, Integer>{

}

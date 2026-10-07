package com.repository.SA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.SA.RoleRightsMST;

@Repository
public interface RoleRightsRepository extends JpaRepository<RoleRightsMST, Long> {

	boolean existsByRolecode(String rolecode);

	List<RoleRightsMST> findByStatus(String string);

}

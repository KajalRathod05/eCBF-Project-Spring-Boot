package com.repository.SA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.SA.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	boolean existsByEmailid(String emailid);

	boolean existsByUserid(String userid);

	boolean existsByEmployeecode(String employeecode);

	Optional<Employee> findByEmployeeid(String employeeid);

}

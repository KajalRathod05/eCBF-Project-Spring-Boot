package com.service.SA;

import java.util.List;

import com.DTOs.SA.EmployeeRequest;
import com.DTOs.SA.EmployeeResponse;
import com.exception.SA.EmployeeException;

public interface EmployeeService {

	void addEmployee(EmployeeRequest employeeRequest)throws EmployeeException;

	List<EmployeeResponse> getAllEmployees();

	void updateEmployee(String employeeid, EmployeeRequest employeeRequest)throws EmployeeException;

	void deleteEmployee(String employeeid);

}

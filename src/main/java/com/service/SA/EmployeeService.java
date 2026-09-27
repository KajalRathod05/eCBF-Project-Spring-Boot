package com.service.SA;
import java.util.List;
import com.DTOs.SA.EmployeeRequest;
import com.DTOs.SA.EmployeeResponse;
import com.exception.GlobalException;

public interface EmployeeService {

	void addEmployee(EmployeeRequest employeeRequest)throws GlobalException;

	List<EmployeeResponse> getAllEmployees();

	void updateEmployee(String employeeid, EmployeeRequest employeeRequest)throws GlobalException;

	void deleteEmployee(String employeeid);

}

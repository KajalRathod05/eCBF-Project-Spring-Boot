package com.serviceImpl.SA;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.DTOs.SA.EmployeeRequest;
import com.DTOs.SA.EmployeeResponse;
import com.exception.SA.EmployeeException;
import com.model.SA.Employee;
import com.repository.SA.EmployeeRepository;
import com.service.SA.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;

	
	
	@Override
    public void addEmployee(EmployeeRequest employeeRequest) {

        if (employeeRepository.existsByEmailid(employeeRequest.getEmailid())) {
            throw new EmployeeException("Employee already exists with this email ID");
        }

        if (employeeRepository.existsByUserid(employeeRequest.getUserid())) {
            throw new EmployeeException("Employee already exists with this User ID");
        }

        if (employeeRepository.existsByEmployeecode(employeeRequest.getEmployeecode())) {
            throw new EmployeeException("Employee already exists with this Employee Code");
        }
        // Map EmployeeRequest DTO -> Employee Entity
        Employee employee = mapToEntity(employeeRequest);

        employeeRepository.save(employee);
    }
	
	@Override
	public void updateEmployee(String employeeid, EmployeeRequest employeeRequest) throws EmployeeException {
	    Employee existingEmployee = employeeRepository.findByEmployeeid(employeeid)
	            .orElseThrow(() -> new EmployeeException("Employee not found with ID: " + employeeid));
	    
	    if (!existingEmployee.getEmailid().equalsIgnoreCase(employeeRequest.getEmailid()) &&
	            employeeRepository.existsByEmailid(employeeRequest.getEmailid())) {
            throw new EmployeeException("Email ID is already registered with another employee");
        }

        if (!existingEmployee.getUserid().equalsIgnoreCase(employeeRequest.getUserid()) &&
            employeeRepository.existsByUserid(employeeRequest.getUserid())) {
            throw new EmployeeException("User ID is already registered with another employee");
        }

        if (!existingEmployee.getEmployeecode().equalsIgnoreCase(employeeRequest.getEmployeecode()) &&
            employeeRepository.existsByEmployeecode(employeeRequest.getEmployeecode())) {
            throw new EmployeeException("Employee Code is already registered with another employee");
        }

	    // 2. Map updated fields from DTO to entity
	    existingEmployee.setName(employeeRequest.getName());
	    existingEmployee.setEmployeecode(employeeRequest.getEmployeecode());
	    existingEmployee.setBranch(employeeRequest.getBranch());
	    existingEmployee.setDepartment(employeeRequest.getDepartment());
	    existingEmployee.setRole(employeeRequest.getRole());
	    existingEmployee.setEmployeetype(employeeRequest.getEmployeetype());
	    existingEmployee.setGrade(employeeRequest.getGrade());
	    existingEmployee.setUserclassification(employeeRequest.getUserclassification());
	    existingEmployee.setUserid(employeeRequest.getUserid());
	    existingEmployee.setMobileno(employeeRequest.getMobileno());
	    existingEmployee.setEmailid(employeeRequest.getEmailid());
	    existingEmployee.setExpirydate(employeeRequest.getExpirydate());
	    existingEmployee.setStatus(employeeRequest.getStatus());
	    existingEmployee.setRemarks(employeeRequest.getRemarks());

	    // Map nested transaction role object if applicable
//	    if (employeeRequest.getTransactionrole() != null) {
//	        existingEmployee.setTransactionrole(employeeRequest.getTransactionrole());
//	    }

	    // 3. Save updated employee
	    employeeRepository.save(existingEmployee);
	}

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employee -> mapToResponse(employee))
                .collect(Collectors.toList());
    }

    // Helper method: Request DTO to Entity
    private Employee mapToEntity(EmployeeRequest dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmployeecode(dto.getEmployeecode());
        employee.setBranch(dto.getBranch());
        employee.setDepartment(dto.getDepartment());
        employee.setRole(dto.getRole());
        employee.setMaker(dto.isMaker());
        employee.setReviewer(dto.isReviewer());
        employee.setChecker(dto.isChecker());
        employee.setEmployeetype(dto.getEmployeetype());
        employee.setGrade(dto.getGrade());
        employee.setUserclassification(dto.getUserclassification());
        employee.setUserid(dto.getUserid());
        employee.setMobileno(dto.getMobileno());
        employee.setEmailid(dto.getEmailid());
        employee.setExpirydate(dto.getExpirydate());
        employee.setStatus(dto.getStatus());
        employee.setRemarks(dto.getRemarks());
        return employee;
    }

    // Helper method: Entity to Response DTO
    private EmployeeResponse mapToResponse(Employee entity) {
        EmployeeResponse response = new EmployeeResponse();
        response.setEmployeeid(entity.getEmployeeid());
        response.setName(entity.getName());
        response.setEmployeecode(entity.getEmployeecode());
        response.setBranch(entity.getBranch());
        response.setDepartment(entity.getDepartment());
        response.setRole(entity.getRole());
        response.setMaker(entity.isMaker());
        response.setReviewer(entity.isReviewer());
        response.setChecker(entity.isChecker());
        response.setEmployeetype(entity.getEmployeetype());
        response.setGrade(entity.getGrade());
        response.setUserclassification(entity.getUserclassification());
        response.setUserid(entity.getUserid());
        response.setMobileno(entity.getMobileno());
        response.setEmailid(entity.getEmailid());
        response.setExpirydate(entity.getExpirydate());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        return response;
    }

    @Override
    public void deleteEmployee(String employeeid) {
        Employee employee = employeeRepository.findByEmployeeid(employeeid)
                .orElseThrow(() -> new EmployeeException("Employee not found with ID: " + employeeid));

        if ("Active".equalsIgnoreCase(employee.getStatus())) {
            throw new EmployeeException("Cannot delete an active employee. Deactivate the employee first.");
        }

        employeeRepository.delete(employee);
    }
}

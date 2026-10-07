package com.controller.SA;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.DTOs.SA.EmployeeRequest;
import com.DTOs.SA.EmployeeResponse;
import com.service.SA.EmployeeService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/employee")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@PreAuthorize("@permissionService.hasPermission(authentication, 1, 'ADD')")
    @PostMapping("/addEmployee")
    public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody EmployeeRequest employeeRequest) {
    	
		System.out.println("ename: "+employeeRequest.getName());    	
    	Map<String, Object> response = new HashMap<>();
        employeeService.addEmployee(employeeRequest);       
        response.put("status", "success");
        response.put("message", "Employee added successfully!");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<Map<String, Object>> getAllEmployees() {

		Map<String, Object> response = new HashMap<>();
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        response.put("status", "success");
        response.put("message", "Employees fetched successfully!");
        response.put("employees", employees);

        return ResponseEntity.ok(response);
    }
    
    @PreAuthorize("@permissionService.hasPermission(authentication, 1, 'VIEW')")
    @GetMapping("/getEmployee/{employeeid}")
    public ResponseEntity<Map<String, Object>> getEmployee(@PathVariable String employeeid) {

		Map<String, Object> response = new HashMap<>();
        EmployeeResponse employee = employeeService.getEmployeeById(employeeid);
        response.put("status", "success");
        response.put("message", "Employee fetched successfully!");
        response.put("employee", employee);

        return ResponseEntity.ok(response);
    }

	@PreAuthorize("@permissionService.hasPermission(authentication, 1, 'EDIT')")
    @PutMapping("/updateEmployee/{employeeid}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable String employeeid, 
                                                 @RequestBody EmployeeRequest employeeRequest) {
    	
		Map<String, Object> response = new HashMap<>();
        employeeService.updateEmployee(employeeid, employeeRequest);        
        response.put("status", "success");
        response.put("message", "Employee updated successfully!");
        
        return ResponseEntity.ok(response);
    }
    
    @PreAuthorize("@permissionService.hasPermission(authentication, 1, 'DELETE')")
    @DeleteMapping("/deleteEmployee/{employeeid}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable String employeeid) {
    	
    	Map<String, Object> response = new HashMap<>();
        employeeService.deleteEmployee(employeeid);    
        response.put("status", "success");
        response.put("message", "Employee deleted successfully!");
        
        return ResponseEntity.ok(response);
        
    }
}

package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTOs.CustomerResponse;
import com.exception.CustomerException;
import com.model.Customer;
import com.model.Customertemp;
import com.service.CustomerService;


@RequestMapping("/customer")
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService custmerService;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<String>  addCustomer(@RequestBody Customertemp customertemp) {	
		
		System.out.println("first name: " + customertemp.getFirstname());
				
		try {
			custmerService.addCustomer(customertemp);
	        return ResponseEntity.ok("Customer added Successful!");
	    } catch (CustomerException e) {

	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(e.getMessage());
	    }
	}
	
	@PostMapping("/getTempCustomers")
	public ResponseEntity<CustomerResponse> getTempCustomers() {

	    CustomerResponse response = new CustomerResponse();
	    try {

	        List<Customertemp> customers = custmerService.getTempCustomers();
	        response.setStatus("success"); 
	        response.setMessage("Customers fetched successfully");
	        response.setData(customers);

	        return ResponseEntity.ok(response);

	    } catch (CustomerException e) {

	        response.setStatus("error");
	        response.setMessage(e.getMessage());
	        response.setData(new ArrayList<>());

	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(response);
	    }
	}
}

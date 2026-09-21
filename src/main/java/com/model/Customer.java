package com.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//personal info
	private long customerid;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileno;
	private Date dob;
	private String gender;
	//Demography details
	private int locationid;
	private int districtid;
	private int stateid;
	private int countryid;
	private long pincode;
	
	//loan deatils
	private Double income;
	private int loantypeid;
	
	//system details dont need to enter
	private int displayflag;
}

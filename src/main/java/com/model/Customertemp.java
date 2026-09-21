package com.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customertemp {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customertempid;
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
	private int deleteflag;//0,1
	private int approveflag;//0,2,3,1,-1
	private int displayflag;
}

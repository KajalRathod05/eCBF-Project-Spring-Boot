package com.model.SA;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeid;
    private String name;
    private String employeecode;
    private String branch;
    private String department;
    private String role;
    private boolean maker;
    private boolean reviewer;
    private boolean checker;
    private String employeetype;
    private String grade;
    private String userclassification;
    private String userid;
    private String mobileno;
    private String emailid;
    private LocalDate expirydate;
    private String status;
    private String remarks;
}

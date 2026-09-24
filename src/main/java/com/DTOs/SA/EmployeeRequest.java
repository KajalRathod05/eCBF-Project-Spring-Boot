package com.DTOs.SA;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeRequest {

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

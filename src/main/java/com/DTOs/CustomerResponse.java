package com.DTOs;

import java.util.List;

import com.model.Customertemp;

import lombok.Data;

@Data
public class CustomerResponse {

	private String status;
    private String message;
    private List<Customertemp> data;
}

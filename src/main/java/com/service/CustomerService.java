package com.service;

import java.util.List;

import com.model.Customer;
import com.model.Customertemp;

public interface CustomerService {

	void addCustomer(Customertemp customer);

	List<Customertemp> getTempCustomers();


}

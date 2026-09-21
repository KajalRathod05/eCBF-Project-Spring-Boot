package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.CustomerException;
import com.model.Customer;
import com.model.Customertemp;
import com.repository.CustomerRepository;
import com.repository.CustomerTempRepository;
import com.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerTempRepository customerTempRepository;

    @Override
    public void addCustomer(Customertemp customertemp) {

        if (customerTempRepository.existsByEmail(customertemp.getEmail())) {
            throw new CustomerException("Customer already exists with this email");
        }

        if (customerTempRepository.existsByMobileno(customertemp.getMobileno())) {
            throw new CustomerException("Customer already exists with this mobile number");
        }
        customertemp.setCustomerid(0);
        customertemp.setDeleteflag(0);
        customertemp.setApproveflag(0);
        customertemp.setDisplayflag(1);
        customerTempRepository.save(customertemp);
    }

	@Override
	public List<Customertemp> getTempCustomers() {	
		return customerTempRepository.findAll();
	}
	
}

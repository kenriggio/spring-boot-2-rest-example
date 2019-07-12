package com.ranker.example.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranker.example.api.model.Customer;
import com.ranker.example.api.repository.CustomerRepository;
import com.ranker.example.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	public CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
		
	}

	@Override
	public Customer findCustomerById(Long id) {
		Customer customer = null;
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if (customerOpt.isPresent()) {
			customer = customerOpt.get();
		}
		return customer;
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}
	

}

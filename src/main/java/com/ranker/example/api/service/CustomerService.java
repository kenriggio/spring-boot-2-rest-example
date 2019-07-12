package com.ranker.example.api.service;

import java.util.List;

import com.ranker.example.api.model.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer findCustomerById(Long id);
	List<Customer> findByLastName(String lastName);
	
}

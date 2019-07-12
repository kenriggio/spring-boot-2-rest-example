package com.ranker.example.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ranker.example.api.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}

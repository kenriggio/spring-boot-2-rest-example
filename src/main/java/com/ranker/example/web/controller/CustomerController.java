package com.ranker.example.web.controller;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ranker.example.api.model.Customer;
import com.ranker.example.api.service.CustomerService;
import com.ranker.example.web.dto.CustomerDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value="Customer Service API", description="Manage customers of our system")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	public CustomerService customerService;
	
	@Autowired
	public ModelMapper modelMapper;

    protected HttpHeaders getResponseHeadersForCustomerDto(CustomerDto customerDto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return responseHeaders;
    }
	
    @ApiOperation(value = "Get Customer by Customer Id", response = CustomerDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved customer"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> findCustomerById(@PathVariable(value="id") Long id) {
    	Customer customer = customerService.findCustomerById(id);
    	if (customer == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id = %d not found", id), null);
    	}
    	CustomerDto customerDto = convertToDto(customer); 
        return new ResponseEntity<Object>(customerDto, getResponseHeadersForCustomerDto(customerDto), HttpStatus.OK);
    }
    
   
    @ApiOperation(value = "Create a customer", response = CustomerDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully created customer"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource")
    })
    @PostMapping()
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customerDto) {
    	Customer customer = null;
    	try {
    		customer = convertFromDto(customerDto);
    		customer = customerService.createCustomer(customer);
    		customerDto = convertToDto(customer); 
    		return new ResponseEntity<Object>(customerDto, getResponseHeadersForCustomerDto(customerDto), HttpStatus.CREATED);
    	} catch (MappingException e) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
    	} catch (Exception e2) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal Server Error", e2);
    	}
    }
    
    private CustomerDto convertToDto(Customer customer) {
    	CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }
    
    private Customer convertFromDto(CustomerDto customerDto) {
    	Customer customer = modelMapper.map(customerDto, Customer.class);
        return customer;
    }
    
    
}
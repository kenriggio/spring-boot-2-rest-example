package com.ranker.example.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about a Customer. ")
public class CustomerDto {
	
	@ApiModelProperty(notes = "The database generated customer ID")
	private Long id;
    
	@ApiModelProperty(notes = "First name of the customer")
	private String firstName;
    
	@ApiModelProperty(notes = "Last name of the customer")
	private String lastName;

    public CustomerDto() {}

    public CustomerDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

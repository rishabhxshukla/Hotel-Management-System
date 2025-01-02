package com.hotel.Manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Staff
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;

	@NotNull(message = "Department ID cannot be null")
	@Min(message = "Department ID must be greater than 0", value = 1)
	private int deptId;
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
	private String name;

	@Min(message = "Age must be at least 18", value = 18)
	private int age;

	@Min(message = "Salary must be non-negative", value = 0)
	private int salary;
	
	@NotBlank(message = "Occupation cannot be blank")
	private String occupation;
	
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
	private String email;
    
    @NotBlank(message = "Address cannot be blank")
    @Size(message = "Address should be between 5 and 100 characters", min = 5, max = 100)
	private String address;
}
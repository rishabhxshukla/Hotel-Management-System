package com.hotel.Owner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Department
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptId;

	@NotBlank(message = "Department name should not be blank")
	@Size(min = 2, max = 50, message = "Department name should have only 2 to 50 characters")
	private String name;

	@NotBlank(message = "Department description should not be blank")
	@Size(min = 2, max = 500, message = "Department description should have only 2 to 500 characters")
	private String description;
}
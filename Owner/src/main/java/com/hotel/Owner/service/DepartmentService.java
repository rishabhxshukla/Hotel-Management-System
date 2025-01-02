package com.hotel.Owner.service;

import com.hotel.Owner.exception.DepartmentAlreadyExistsException;
import com.hotel.Owner.exception.DepartmentNotFoundException;
import com.hotel.Owner.exception.NoDepartmentsFoundException;
import com.hotel.Owner.model.Department;
import com.hotel.Owner.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService
{
	@Autowired
	private DepartmentRepository repo;

	public List<Department> showAllDepartments()
	{
		//Fetch all departments
		List<Department> departments = repo.findAll();

		//Check if list is empty
		if (departments.isEmpty()) {
			throw new NoDepartmentsFoundException("Table is empty. No departments found in the database!");
		}
		
		return departments;
	}

	public Department showDepartmentById(int id)
	{
		//Check if department is not present
		if (!repo.findById(id).isPresent()) {
			throw new DepartmentNotFoundException("Department with ID " + id + " not found!");
		}

		return repo.findById(id).get();
	}

	public void addDepartment(Department department)
	{
		if (repo.existsByName(department.getName())) {
			throw new DepartmentAlreadyExistsException("Department " + department.getName() + " already exists!");
		}

		repo.save(department);
	}

	public void updateDepartment(int id, Department requestBody)
	{
		//Check if department is not present
		if (!repo.findById(id).isPresent()) {
			throw new DepartmentNotFoundException("Department with ID " + id + " not found!");
		}

		//Fetch the department
		Department department = repo.findById(id).get();

		//Change values coming from request body
		department.setName(requestBody.getName());
		department.setDescription(requestBody.getDescription());

		//Save the department
		repo.save(department);
	}

	public void deleteDepartment(int id) 
	{
		//Check if department is not present
		if (!repo.findById(id).isPresent()) {
			throw new DepartmentNotFoundException("Department with ID " + id + " not found!");

		}

		repo.deleteById(id);
	}
}
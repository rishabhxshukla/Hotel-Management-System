package com.hotel.Manager.service;

import com.hotel.Manager.exception.NoStaffFoundException;
import com.hotel.Manager.exception.StaffNotFoundException;
import com.hotel.Manager.model.Staff;
import com.hotel.Manager.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService
{
	@Autowired
	private StaffRepository repo;

	public List<Staff> showAllStaffs()
	{
		//Fetch all staffs
	    List<Staff> staffs = repo.findAll();

		//Check if list is empty
	    if (staffs.isEmpty()) {
	        throw new NoStaffFoundException("Table is empty. No staffs found in the database!");
	    }

	    return staffs;
	}

	public Staff showStaffById(int id)
	{
		//Check if staff is not present
		if (!repo.findById(id).isPresent()) {
			throw new StaffNotFoundException("Staff with ID " + id + " not found!");
		}

		return repo.findById(id).get();
	}

	public void addStaff(Staff staff)
	{
		repo.save(staff);
	}

	public void updateStaff(int id, Staff requestBody)
	{
		//Check if staff is not present
		if (!repo.findById(id).isPresent()) {
			throw new StaffNotFoundException("Staff with ID " + id + " not found!");
		}

		//Fetch the staff
		Staff staff = repo.findById(id).get();

		//Change values coming from request body
		staff.setDeptId(requestBody.getDeptId());
		staff.setName(requestBody.getName());
		staff.setAge(requestBody.getAge());
		staff.setSalary(requestBody.getSalary());
		staff.setOccupation(requestBody.getOccupation());
		staff.setEmail(requestBody.getEmail());
		staff.setAddress(requestBody.getAddress());

		//Save the staff
		repo.save(staff);
	}

	public void deleteStaff(int id)
	{
		//Check if staff is not present
		if (!repo.findById(id).isPresent()) {
			throw new StaffNotFoundException("Staff with ID " + id + " not found!");
		}

		repo.deleteById(id);
	}
}
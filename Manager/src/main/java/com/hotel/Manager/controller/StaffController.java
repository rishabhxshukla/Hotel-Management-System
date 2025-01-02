package com.hotel.Manager.controller;

import com.hotel.Manager.model.Staff;
import com.hotel.Manager.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/staffDetails")
public class StaffController
{
	@Autowired
	private StaffService service;

	@GetMapping("/show")
	public List<Staff> showAllStaffs()
	{
		return service.showAllStaffs();
	}

	@GetMapping("/show/{id}")
	public Staff showStaffById(@PathVariable int id)
	{
		return service.showStaffById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addStaff(@Valid @RequestBody Staff staff)
	{
		service.addStaff(staff);

		return new ResponseEntity<>("Staff inserted successfully!", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateStaff(@PathVariable int id, @Valid @RequestBody Staff staff)
	{
		service.updateStaff(id, staff);

		return new ResponseEntity<>("Staff updated successfully",HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteStaff(@PathVariable int id)
	{
		service.deleteStaff(id);

		return new ResponseEntity<>("Staff deleted successfully!", HttpStatus.OK);
	}
}
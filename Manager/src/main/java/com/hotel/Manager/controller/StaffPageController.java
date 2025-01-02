package com.hotel.Manager.controller;

import com.hotel.Manager.model.Staff;
import com.hotel.Manager.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staffPage")
public class StaffPageController
{
	@Autowired
    private StaffService service;
	
    /* Method to render index.html */
    @GetMapping("/")
    public String index()
    {
        return "index"; 
    }

    @PostMapping("/manager/staffDetails/add")
    public ResponseEntity<String> addStaff(@Valid @RequestBody Staff staff)
    {
        service.addStaff(staff);

        return new ResponseEntity<>("Staff inserted successfully!", HttpStatus.OK);
    }
}
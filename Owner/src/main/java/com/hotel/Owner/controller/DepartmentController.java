package com.hotel.Owner.controller;

import com.hotel.Owner.model.Department;
import com.hotel.Owner.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner/departmentDetails")
public class DepartmentController
{
    @Autowired
    private DepartmentService service;

    @GetMapping("/show")
    public List<Department> showAllDepartments()
    {
        return service.showAllDepartments();
    }

    @GetMapping("/show/{id}")
    public Department showDepartmentById(@PathVariable int id)
    {
        return service.showDepartmentById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDepartment(@Valid @RequestBody Department department)
    {
        service.addDepartment(department);

        return new ResponseEntity<>("Department inserted successfully!", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable int id, @Valid @RequestBody Department department) 
    {
        service.updateDepartment(id, department);

        return new ResponseEntity<>("Department updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id)
    {
        service.deleteDepartment(id);

        return new ResponseEntity<>("Department deleted successfully!", HttpStatus.OK);
    }
}
package com.hotel.Manager;

import com.hotel.Manager.exception.NoStaffFoundException;
import com.hotel.Manager.exception.StaffNotFoundException;
import com.hotel.Manager.model.Staff;
import com.hotel.Manager.repository.StaffRepository;
import com.hotel.Manager.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;  // Mock the repository

    @InjectMocks
    private StaffService staffService;  // Inject the mocked repository into the service

    private Staff staff;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        
        // Initialize a Staff object to use in tests
        staff = new Staff();
        staff.setStaffId(1);
        staff.setDeptId(101);
        staff.setName("John Doe");
        staff.setAge(30);
        staff.setSalary(5000);
        staff.setOccupation("Manager");
        staff.setEmail("john.doe@example.com");
        staff.setAddress("123 Street, City, Country");
    }

    @Test
    void testShowAllStaffs_WhenNoStaffFound() {
        // Mock the repository to return an empty list
        when(staffRepository.findAll()).thenReturn(Arrays.asList());

        // Assert that NoStaffFoundException is thrown
        assertThrows(NoStaffFoundException.class, () -> staffService.showAllStaffs());

        // Verify the repository's findAll method was called once
        verify(staffRepository, times(1)).findAll();
    }

    @Test
    void testShowAllStaffs_WhenStaffFound() {
        // Mock the repository to return a list of staff
        when(staffRepository.findAll()).thenReturn(Arrays.asList(staff));

        // Call the service method
        List<Staff> result = staffService.showAllStaffs();

        // Assert that the list is not null, contains one staff, and check the staff details
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());

        // Verify the repository's findAll method was called once
        verify(staffRepository, times(1)).findAll();
    }

    @Test
    void testShowStaffById_WhenStaffNotFound() {
        // Mock the repository to return an empty Optional when searching by id
        when(staffRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that StaffNotFoundException is thrown
        assertThrows(StaffNotFoundException.class, () -> staffService.showStaffById(1));

        // Verify the repository's findById method was called once
        verify(staffRepository, times(1)).findById(1);
    }

    @Test
    void testShowStaffById_WhenStaffFound() {
        // Mock the repository to return the staff when searching by id
        when(staffRepository.findById(1)).thenReturn(Optional.of(staff));

        // Call the service method
        Staff result = staffService.showStaffById(1);

        // Assert the staff's details
        assertNotNull(result);
        assertEquals(1, result.getStaffId());
        assertEquals("John Doe", result.getName());

        // Verify the repository's findById method was called twice
        verify(staffRepository, times(2)).findById(1);
    }

    @Test
    void testAddStaff() {
        // Mock the save method of the repository to return the staff
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);

        // Call the service method to add the staff
        staffService.addStaff(staff);

        // Verify the repository's save method was called once
        verify(staffRepository, times(1)).save(any(Staff.class));
    }

    @Test
    void testUpdateStaff_WhenStaffNotFound() {
        // Prepare an updated staff object
        Staff updatedStaff = new Staff();
        updatedStaff.setDeptId(102);
        updatedStaff.setName("Jane Doe");
        updatedStaff.setAge(35);
        updatedStaff.setSalary(5500);
        updatedStaff.setOccupation("Assistant Manager");
        updatedStaff.setEmail("jane.doe@example.com");
        updatedStaff.setAddress("456 Street, City, Country");

        // Mock the repository to return an empty Optional when searching by id
        when(staffRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that StaffNotFoundException is thrown
        assertThrows(StaffNotFoundException.class, () -> staffService.updateStaff(1, updatedStaff));

        // Verify the repository's findById method was called once
        verify(staffRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateStaff_WhenStaffFound() {
        // Prepare an updated staff object
        Staff updatedStaff = new Staff();
        updatedStaff.setDeptId(102);
        updatedStaff.setName("Jane Doe");
        updatedStaff.setAge(35);
        updatedStaff.setSalary(5500);
        updatedStaff.setOccupation("Assistant Manager");
        updatedStaff.setEmail("jane.doe@example.com");
        updatedStaff.setAddress("456 Street, City, Country");

        // Mock the repository to return the existing staff when searching by id
        when(staffRepository.findById(1)).thenReturn(Optional.of(staff));

        // Mock the repository save method to return the updated staff
        when(staffRepository.save(any(Staff.class))).thenReturn(updatedStaff);

        // Call the service method to update the staff
        staffService.updateStaff(1, updatedStaff);

        // Verify that the save method was called once
        verify(staffRepository, times(1)).save(any(Staff.class));
    }

    @Test
    void testDeleteStaff_WhenStaffNotFound() {
        // Mock the repository to return an empty Optional when searching by id
        when(staffRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that StaffNotFoundException is thrown
        assertThrows(StaffNotFoundException.class, () -> staffService.deleteStaff(1));

        // Verify the repository's findById method was called once
        verify(staffRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteStaff_WhenStaffFound() {
        // Mock the repository to return the staff when searching by id
        when(staffRepository.findById(1)).thenReturn(Optional.of(staff));

        // Call the service method to delete the staff
        staffService.deleteStaff(1);

        // Verify the repository's deleteById method was called once
        verify(staffRepository, times(1)).deleteById(1);
    }
}

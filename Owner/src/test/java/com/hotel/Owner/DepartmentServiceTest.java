package com.hotel.Owner;

import com.hotel.Owner.exception.DepartmentNotFoundException;
import com.hotel.Owner.model.Department;
import com.hotel.Owner.repository.DepartmentRepository;
import com.hotel.Owner.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
        department = new Department();
        department.setDeptId(1);
        department.setName("HR");
        department.setDescription("Human Resources Department");
    }

    @Test
    public void testCreateDepartment() {
        when(departmentRepository.save(department)).thenReturn(department);

        departmentService.addDepartment(department);

        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void testGetAllDepartments() {
        when(departmentRepository.findAll()).thenReturn(List.of(department));

        var result = departmentService.showAllDepartments();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetDepartmentById_NotFound() {
        when(departmentRepository.findById(department.getDeptId())).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.showDepartmentById(department.getDeptId()));
    }

    @Test
    public void testUpdateDepartment() {
        when(departmentRepository.findById(department.getDeptId())).thenReturn(Optional.of(department));
        Department updatedDepartment = new Department();
        updatedDepartment.setName("IT");
        updatedDepartment.setDescription("Information Technology Department");

        departmentService.updateDepartment(department.getDeptId(), updatedDepartment);

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testUpdateDepartment_NotFound() {
        when(departmentRepository.findById(department.getDeptId())).thenReturn(Optional.empty());

        Department updatedDepartment = new Department();
        updatedDepartment.setName("IT");
        updatedDepartment.setDescription("Information Technology Department");

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.updateDepartment(department.getDeptId(), updatedDepartment));
    }

    @Test
    public void testDeleteDepartment() {
        when(departmentRepository.findById(department.getDeptId())).thenReturn(Optional.of(department));

        departmentService.deleteDepartment(department.getDeptId());

        verify(departmentRepository, times(1)).deleteById(department.getDeptId());
    }

    @Test
    public void testDeleteDepartment_NotFound() {
        when(departmentRepository.findById(department.getDeptId())).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.deleteDepartment(department.getDeptId()));
    }
}

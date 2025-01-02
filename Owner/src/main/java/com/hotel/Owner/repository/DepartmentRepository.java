package com.hotel.Owner.repository;

import com.hotel.Owner.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
    boolean existsByName(String name);
}
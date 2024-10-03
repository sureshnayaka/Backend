package com.thiruacademy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thiruacademy.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}

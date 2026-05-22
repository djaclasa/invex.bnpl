package com.invex.rest.bnpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invex.rest.bnpl.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByName(String name);

}
package com.invex.rest.bnpl.service;

import java.util.List;

import com.invex.rest.bnpl.entity.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee Employee);

	public String deleteEmployee(long id_Employee);

	public List<Employee> getAllEmployees();

	public Employee getEmployee(Long id_Employee);
	
	public Employee getEmployee(String name);

	public Employee upgradeEmployee(Employee nuevoEmployee, Long id_Employee);

}
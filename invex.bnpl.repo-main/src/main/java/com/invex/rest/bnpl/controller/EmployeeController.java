package com.invex.rest.bnpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invex.rest.bnpl.entity.Employee;
import com.invex.rest.bnpl.service.EmployeeService;

@RestController
@RequestMapping(path = "/invexapp")
public class EmployeeController {

	@Autowired
	private final EmployeeService service;

	EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/Employees")
	List<Employee> all() {
		return service.getAllEmployees();
	}

	@PostMapping("/addEmployee")
	String addEmployee(@RequestBody Employee newEmployee) {
		Employee Employee = service.saveEmployee(newEmployee);

		return "Employee " + Employee.getName() + " register OK";
	}

	@GetMapping("/Employee/{id_Employee}")
	Employee getEmployee(@PathVariable Long id_Employee) {
		return service.getEmployee(id_Employee);
	}
	
	@GetMapping("/Employees/")
	Employee getEmployee(@RequestParam(name = "name") String name) {
		return service.getEmployee(name);
	}

	@PutMapping("/Employee/{id_Employee}")
	String updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id_Employee) {
		Employee Employee = service.upgradeEmployee(newEmployee, id_Employee);
		return "Employee " + Employee.getName() + " upgrade OK";
	}

	@DeleteMapping("/deleteEmployee/{id_Employee}")
	String deleteEmployee(@PathVariable Long id_Employee) {
		Employee Employee = service.getEmployee(id_Employee);

		if (Employee != null) {
			service.deleteEmployee(id_Employee);
			return "Employee " + Employee.getName() + " deleted OK";
		} else {
			return "Employee " + id_Employee + " not exist";
		}
	}
}
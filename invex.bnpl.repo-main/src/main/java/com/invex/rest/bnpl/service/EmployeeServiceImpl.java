package com.invex.rest.bnpl.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invex.rest.bnpl.entity.Employee;
import com.invex.rest.bnpl.repository.EmployeeRepository;

@Service("EmployeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private ApplicationEventPublisher events;
	
	 private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee saveEmployee(Employee employee) {
		
		employee.setRegistrationDate(new Date());
		Employee newEmployee = repository.save(employee);
		events.publishEvent(employee);
		return newEmployee;
	}

	@Override
	public String deleteEmployee(long idEmployee) {
		repository.deleteById(idEmployee);
		return "Delete Ok Employee " + idEmployee;
	}

	@Override
	@Cacheable("Employees")
	public List<Employee> getAllEmployees() {
		LOG.info("Get All Employees");
		return repository.findAll();
	}

	@Override
	public Employee getEmployee(Long id_Employee) {

		if (!repository.findById(id_Employee).isEmpty()) {
			return repository.findById(id_Employee).get();
		} else {
			return null;
		}
	}
	
	@Override
	public Employee getEmployee(String name) {
		LOG.info("Get Employee " + name);
		if (repository.findByName(name) != null) {
			return repository.findByName(name);
		} else {
			return null;
		}
	}

	@Override
	public Employee upgradeEmployee(Employee newEmployee, Long id_Employee) {
		LOG.info("Search Employee" + newEmployee.getId_Employee());
		return repository.findById(id_Employee).map(Employee -> {
			LOG.info("Id founded upgrade " + id_Employee);
			Employee.setName(newEmployee.getName());
			Employee.setSecondName(newEmployee.getSecondName());
			Employee.setLastName(newEmployee.getLastName());
			Employee.setSecondLastName(newEmployee.getSecondLastName());
			Employee.setBirthDate(newEmployee.getBirthDate());
			Employee.setActive(newEmployee.getActive());
			Employee.setAge(newEmployee.getAge());
			Employee.setPosition(newEmployee.getPosition());
			Employee.setSex(newEmployee.getSex());
			Employee.setActive(newEmployee.getActive());
			
			return repository.save(Employee);
		}).orElseGet(() -> {
			LOG.info("No id found for Update Employee");
			newEmployee.setId_Employee(id_Employee);
			return repository.save(newEmployee);
		});
	}

}
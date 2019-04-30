package com.unit.testdemo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.repository.EmployeeRepository;
import com.unit.testdemo.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee updateEmployee(Employee user, long l) {

		return employeeRepository.saveAndFlush(user);
	}

	@Override
	public void deleteEmployeeById(long id) {

		employeeRepository.deleteById(id);

	}

}

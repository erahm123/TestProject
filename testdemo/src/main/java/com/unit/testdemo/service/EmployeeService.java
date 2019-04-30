package com.unit.testdemo.service;

import java.util.List;
import java.util.Optional;

import com.unit.testdemo.entity.Employee;

public interface EmployeeService {
	
	public void addEmployee(Employee employee);
    public List<Employee> getAllEmployee();
    public Optional<Employee> findById(long id);
    public Employee updateEmployee(Employee user, long l);
    public void deleteEmployeeById(long id);
    
}

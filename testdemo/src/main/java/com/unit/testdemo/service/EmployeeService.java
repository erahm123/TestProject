package com.unit.testdemo.service;

import java.util.List;

import com.unit.testdemo.dto.EmployeeRequestDTO;
import com.unit.testdemo.dto.EmployeeResponseDTO;
import com.unit.testdemo.entity.Employee;

public interface EmployeeService {
	
	public void addEmployee(EmployeeRequestDTO employee);
    public List<EmployeeResponseDTO> getAllEmployee();
    public List<EmployeeResponseDTO> findById(long id);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployeeById(long id);
    
}

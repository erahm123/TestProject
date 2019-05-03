package com.unit.testdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unit.testdemo.dto.EmployeeRequestDTO;
import com.unit.testdemo.dto.EmployeeResponseDTO;
import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/emp")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@ApiOperation(value = "get all employee", response = List.class)
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<EmployeeResponseDTO> getAllEmployee() {

		log.info("employee fetch  ctrl :: START ");
		List<EmployeeResponseDTO> employee = employeeService.getAllEmployee();
		log.info("employee fetch ctrl :: END ");
		return employee;
	}

	@ApiOperation(value = "add employee")
	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<String> addEmployee(
			@ApiParam(value = "Employee object store in database table", required = true) @RequestBody EmployeeRequestDTO employee) {
		employeeService.addEmployee(employee);
		return new ResponseEntity<String>("" + employee.getId(), HttpStatus.CREATED);

	}

	@ApiOperation(value = "delete employee by id")
	@DeleteMapping(value = "/{id}", headers = "Accept ")
	public ResponseEntity<Void> deleteEmployeeById(
			@ApiParam(value = "delete employee", required = true) @PathVariable("id") long id) {

		List<EmployeeResponseDTO> employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		employeeService.deleteEmployeeById(employee.get(0).getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation(value = "update employee")
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateEmployee(
			@ApiParam(value = "update employee", required = true) @RequestBody Employee employee) {
		List<EmployeeResponseDTO> empList = employeeService.findById(employee.getId());
		if (empList == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}else
		{
			employeeService.updateEmployee(employee);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
		}
	}


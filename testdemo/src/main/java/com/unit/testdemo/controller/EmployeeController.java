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
import org.springframework.web.util.UriComponentsBuilder;

import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Employee> getAllEmployee() {

		List<Employee> employee = employeeService.getAllEmployee();

		return employee;
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		employeeService.addEmployee(employee);
		return new ResponseEntity<String>("" + employee.getId(), HttpStatus.CREATED);

	}

	@DeleteMapping(value = "/{id}", headers = "Accept ")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") long id) {

		Optional<Employee> employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody Employee employee) {
		Optional<Employee> user = employeeService.findById(employee.getId());
		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		employeeService.updateEmployee(employee, employee.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}

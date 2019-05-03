package com.unit.testdemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.unit.testdemo.controller.EmployeeController;
import com.unit.testdemo.dto.EmployeeRequestDTO;
import com.unit.testdemo.dto.EmployeeResponseDTO;
import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTests {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController = new EmployeeController();

	@Test
	public void testAddEmployee() throws Exception {

		EmployeeRequestDTO employee = new EmployeeRequestDTO();
		employee.setDesignation("dev");
		employee.setFirstName("nets");
		employee.setLastName("nets1");
		employee.setId(1L);
		employee.setEmployeeCode("A");
		Mockito.doNothing().when(employeeService).addEmployee(employee);
		ResponseEntity<String> responseEntity = employeeController.addEmployee(employee);
		assertEquals("1", responseEntity.getBody());
		
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());

	}

	@Test
	public void testGetEmployeeList() {
		
		
		List<EmployeeResponseDTO> empList = new ArrayList<EmployeeResponseDTO>();
		EmployeeResponseDTO emp = new EmployeeResponseDTO(1, "nets", "nets1", "A", "dev");
		EmployeeResponseDTO emp1 = new EmployeeResponseDTO(2, "nets", "nets1", "B", "tst");
		empList.add(emp);
		empList.add(emp1);
		
		Mockito.when(employeeService.getAllEmployee()).thenReturn(empList);
		List<EmployeeResponseDTO> responseEntity = employeeController.getAllEmployee();
		assertEquals(2, responseEntity.size());
		

	}

	@Test
	public void testUpdateEmployee() {
		
		Employee emp1 = new Employee(2, "nets", "nets1", "B", "tst");
		
		Mockito.when(employeeService.updateEmployee(emp1)).thenReturn(emp1);

		ResponseEntity<String> responseEntity =employeeController.updateEmployee(emp1);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());

	}
	
}

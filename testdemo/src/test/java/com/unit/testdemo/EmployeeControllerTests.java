package com.unit.testdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import com.unit.testdemo.controller.EmployeeController;
import com.unit.testdemo.dto.EmployeeRequestDTO;
import com.unit.testdemo.dto.EmployeeResponseDTO;
import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.queue.config.EmployeeMessageSender;
import com.unit.testdemo.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTests {

	@Mock
	EmployeeService employeeService;
	
	@Mock
	EmployeeMessageSender employeeMessageSenderMock ;

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
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("1", responseEntity.getBody());
		
	}
	
	@Test
	public void tesAddEmptyEmployee ()
	{
		
		ResponseEntity<String> responseEntity = employeeController.addEmployee(null);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		
	}

	@Test
	public void testGetEmployeeList()
	{
		
		List<EmployeeResponseDTO> empList = new ArrayList<EmployeeResponseDTO>();
		EmployeeResponseDTO emp = new EmployeeResponseDTO(1, "nets", "nets1", "A", "dev");
		EmployeeResponseDTO emp1 = new EmployeeResponseDTO(2, "nets", "nets1", "B", "tst");
		empList.add(emp);
		empList.add(emp1);
		Mockito.when(employeeService.getAllEmployee()).thenReturn(empList);
		List<EmployeeResponseDTO> employeeResponseDTO = employeeController.getAllEmployee();
		assertEquals(2, employeeResponseDTO.size());

	}

	@Test
	public void testUpdateEmployee() throws Exception 
	{
		
		Employee emp1 = new Employee(2, "nets", "nets1", "B", "tst");
		Mockito.when(employeeService.updateEmployee(emp1)).thenReturn(emp1);
		ResponseEntity<String> responseEntity =employeeController.updateEmployee(emp1);
		
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNull(responseEntity.getBody());

	}
	
	@Test
	public void testEmployeeInterceptCreated() throws Exception
	{
		URI location = new URI("/add");
		DefaultResponseCreator responseCreator = MockRestResponseCreators.withCreatedEntity(location);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(location, response.getHeaders().getLocation());
		assertNotNull(response);
		
	}
	
	@Test
	public void testFindByEmpId() throws Exception
	{
		
		List<EmployeeResponseDTO> empList = new ArrayList<EmployeeResponseDTO>();
		EmployeeResponseDTO emp = new EmployeeResponseDTO(1, "nets", "nets1", "A", "dev");
		EmployeeResponseDTO emp1 = new EmployeeResponseDTO(2, "nets", "nets1", "B", "tst");
		empList.add(emp);
		empList.add(emp1);
		List<EmployeeResponseDTO> employee = employeeService.findById(emp.getId());
		assertNotNull(employee);
		assertEquals(1,emp.getId());
		assertEquals(2,empList.size());
		
	}
	
	@Test
	public void testServerError() throws Exception
	{
		DefaultResponseCreator responseCreator = MockRestResponseCreators.withServerError();
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getHeaders().isEmpty());
	}
	
}

package com.unit.testdemo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.unit.testdemo.dto.EmployeeRequestDTO;
import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.repository.EmployeeRepository;
import com.unit.testdemo.service.EmployeeService;
import com.unit.testdemo.service.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTests {

	@InjectMocks
	EmployeeService employeeServiceMock = new EmployeeServiceImpl();

	@Mock
	static EmployeeRepository employeeRepository;

	@Test
	public void testGetAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		Employee emp = new Employee(1, "nets", "nets1", "A", "dev");
		Employee emp1 = new Employee(2, "nets", "nets1", "B", "tst");
		empList.add(emp);
		empList.add(emp1);
		when(employeeRepository.findAll()).thenReturn(empList);
		employeeServiceMock.getAllEmployee();
		assertEquals(2, empList.size());
	}

	@Test
	public void testAddEmployee() {
		EmployeeRequestDTO emp = new EmployeeRequestDTO(3, "nets", "nets1", "B", "tst");
		employeeServiceMock.addEmployee(emp);
	}

	@Test
	public void testDeleteEmployeeById() {
		Employee emp = new Employee(1, "nets", "nets1", "A", "dev");

		employeeServiceMock.deleteEmployeeById(emp.getId());
		verify(employeeRepository, times(1)).deleteById(emp.getId());
	}

	@Test
	public void testUpdateEmployee() {
		Employee emp1 = new Employee(2, "nets", "nets1", "B", "tst");

		employeeServiceMock.updateEmployee(emp1);
		verify(employeeRepository, times(1)).saveAndFlush(emp1);

	}

}

package com.unit.testdemo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.unit.testdemo.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTests {

	// @Autowired
	// private MockMvc mockMvc;

	Employee emp1 = new Employee(2, "nets", "nets1", "B", "tst");

	HttpHeaders headers = new HttpHeaders();

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void testAddEmployee() throws Exception {
		Employee emp = new Employee(1, "nets", "nets1", "A", "dev");
		ResponseEntity<String> response = restTemplate.postForEntity(createURLWithPort("/emp/add"), emp, String.class);
		assertTrue(response.getBody().contains("1"));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	/*
	 * @Test public void testGetAllEmployee() throws Exception {
	 * 
	 * List<Employee> employee = employeeController.getAllEmployee();
	 * //assertEquals(employee.get(0), "fetch"); }
	 */

	/*
	 * @Test public void testAddEmployee() { }
	 */

	@Test
	public void testDeleteEmployeeById() {
	}

	@Test
	public void testUpdateEmployee() {

	}

}

/*package com.unit.testdemo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.repository.EmployeeRepository;
import com.unit.testdemo.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeControllerTests.class)
public class EmployeeControllerTests
{
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Mock
	private EmployeeService employeeService;
	
	@Mock
	private static EmployeeRepository employeeRepository;
	
	String empObjInJSON = null;
	
	//RestTemplate restTemplate;
    //ResponseEntity response;
    //@Rule
    // public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));
	
	@Test
	public void testAddEmployees() throws JsonProcessingException
	{
		Employee employee = new Employee();
		employee.setDesignation("dev");
		employee.setFirstName("nets");
		employee.setLastName("nets1");
		employee.setId(1L);
		employee.setEmployeeCode("A");
		
		org.mockito.Mockito.when(employeeService.addEmployee(employee)).thenReturn(employee.getFirstName());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/emp/get").accept(MediaType.APPLICATION_JSON)
				.content(empObjInJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJSON = response.getContentAsString();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	public String mapToJSON(Object object) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
	@Test
	public void addEmployeeAPI() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .post("/add")
	      .content(mapToJSON(new Employee(1L, "hello", "hello123", "hello","A")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      //.andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	
	
	@Test
	public void getEmployeeById() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/emp/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	@Test
	public void testAddEmployee()
	{
	}
	
	@Test
	public void testDeleteEmployeeById()
	{
	}
	
	@Test
	public void testUpdateEmployee()
	{

	}
	
}
*/
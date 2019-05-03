package com.unit.testdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
	
	private long id;
	private String firstName;
	private String lastName;
	private String employeeCode;
	private String designation;

}

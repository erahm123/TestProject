package com.unit.testdemo.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String firstName;
	private String lastName;
	private String employeeCode;
	private String designation;
}

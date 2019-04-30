package com.unit.testdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "employee")
public class Employee {
	
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String employeeCode;
	private String designation;
}

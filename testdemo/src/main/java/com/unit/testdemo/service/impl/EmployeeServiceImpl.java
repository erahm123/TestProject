package com.unit.testdemo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.unit.testdemo.dto.EmployeeRequestDTO;
import com.unit.testdemo.dto.EmployeeResponseDTO;
import com.unit.testdemo.entity.Employee;
import com.unit.testdemo.repository.EmployeeRepository;
import com.unit.testdemo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(EmployeeRequestDTO empReqDTO) {
		
		log.info("employee add :: START ");
		ModelMapper mapper = new ModelMapper();
		Employee employee = mapper.map(empReqDTO, Employee.class);
		employeeRepository.save(employee);
		log.debug("employee add :: END ");

	}

	@Override
	public List<EmployeeResponseDTO> getAllEmployee() {
		
		
		log.info("employee fetch :: START ");
		ModelMapper mapper = new ModelMapper();
		List<Employee> empList=employeeRepository.findAll();
		java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeResponseDTO>>(){}.getType();
		List<EmployeeResponseDTO> empResponseList = mapper.map(empList, targetListType);
		log.info("employee fetch : END");
		return empResponseList;
		
	}

	@Override
	public List<EmployeeResponseDTO> findById(long id) {
		
		log.info("employee find :: START");
		ModelMapper mapper = new ModelMapper();
		Optional<Employee> employeeOpt =  employeeRepository.findById(id);
		List<Employee> list = new ArrayList<>();
		if ( employeeOpt.isPresent() )
		list.add( employeeOpt.get() );
		java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeResponseDTO>>(){}.getType();
		List<EmployeeResponseDTO> empResponseList = mapper.map(list, targetListType);
		log.info("employee find :: END");
		return empResponseList;
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		return employeeRepository.saveAndFlush(employee);
	}

	@Override
	public void deleteEmployeeById(long id) {

		employeeRepository.deleteById(id);

	}

}

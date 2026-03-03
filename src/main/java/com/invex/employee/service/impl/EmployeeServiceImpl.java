package com.invex.employee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.invex.employee.dto.EmployeeRequest;
import com.invex.employee.dto.EmployeeResponse;
import com.invex.employee.entity.Employee;
import com.invex.employee.exception.ResourceNotFoundException;
import com.invex.employee.mapper.EmployeeMapper;
import com.invex.employee.repository.EmployeeRepository;
import com.invex.employee.service.EmployeeService;
import com.invex.employee.util.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repository;
	private final EmployeeMapper mapper;

	@Override
	public List<EmployeeResponse> findAll() {
		return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponse findById(Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.EMPLOYEE_NOT_FOUND + id));

		return mapper.toResponse(employee);
	}

	@Override
	public List<EmployeeResponse> create(List<EmployeeRequest> requestList) {
		List<Employee> employees = requestList.stream().map(mapper::toEntity).collect(Collectors.toList());

		return repository.saveAll(employees).stream().map(mapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponse update(Long id, EmployeeRequest request) {

		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.EMPLOYEE_NOT_FOUND + id));

		mapper.updateEntity(employee, request);

		return mapper.toResponse(repository.save(employee));
	}

	@Override
	public void delete(Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.EMPLOYEE_NOT_FOUND + id));

		repository.delete(employee);
	}

	@Override
	public List<EmployeeResponse> searchByName(String name) {
		return repository.findByFirstNameContainingIgnoreCase(name).stream().map(mapper::toResponse)
				.collect(Collectors.toList());
	}
}
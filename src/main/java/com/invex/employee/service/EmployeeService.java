package com.invex.employee.service;

import com.invex.employee.dto.*;

import java.util.List;

public interface EmployeeService {

	List<EmployeeResponse> findAll();

	EmployeeResponse findById(Long id);

	List<EmployeeResponse> create(List<EmployeeRequest> request);

	EmployeeResponse update(Long id, EmployeeRequest request);

	void delete(Long id);

	List<EmployeeResponse> searchByName(String name);
}
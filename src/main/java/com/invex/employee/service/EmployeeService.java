package com.invex.employee.service;

import com.invex.employee.dto.*;

import java.util.List;

/**
 * Service interface for managing employee operations.
 */
public interface EmployeeService {

	/**
	 * Retrieves all employees.
	 *
	 * @return a list of all employees
	 */
	List<EmployeeResponse> findAll();

	/**
	 * Retrieves an employee by their unique identifier.
	 *
	 * @param id the employee ID
	 * @return the employee details
	 */
	EmployeeResponse findById(Long id);

	/**
	 * Creates a list of new employees.
	 *
	 * @param request the list of employee creation requests
	 * @return the list of created employees
	 */
	List<EmployeeResponse> create(List<EmployeeRequest> request);

	/**
	 * Updates an existing employee's information.
	 *
	 * @param id      the employee ID
	 * @param request the employee update details
	 * @return the updated employee details
	 */
	EmployeeResponse update(Long id, EmployeeRequest request);

	/**
	 * Deletes an employee by their unique identifier.
	 *
	 * @param id the employee ID
	 */
	void delete(Long id);

	/**
	 * Searches for employees by their first name.
	 *
	 * @param name the name to search for
	 * @return a list of employees matching the name
	 */
	List<EmployeeResponse> searchByName(String name);
}
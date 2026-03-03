package com.invex.employee.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.invex.employee.dto.EmployeeRequest;
import com.invex.employee.dto.EmployeeResponse;
import com.invex.employee.entity.Employee;
import com.invex.employee.exception.ResourceNotFoundException;
import com.invex.employee.mapper.EmployeeMapper;
import com.invex.employee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository repository;

	@Mock
	private EmployeeMapper mapper;

	@InjectMocks
	private EmployeeServiceImpl service;

	@Test
	void findAll_ShouldReturnListOfEmployees() {
		// Arrange
		Employee employee = new Employee();
		EmployeeResponse response = new EmployeeResponse();
		when(repository.findAll()).thenReturn(Collections.singletonList(employee));
		when(mapper.toResponse(employee)).thenReturn(response);

		// Act
		List<EmployeeResponse> result = service.findAll();

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(repository).findAll();
		verify(mapper).toResponse(employee);
	}

	@Test
	void findById_ShouldReturnEmployee_WhenIdExists() {
		// Arrange
		Long id = 1L;
		Employee employee = new Employee();
		EmployeeResponse response = new EmployeeResponse();
		when(repository.findById(id)).thenReturn(Optional.of(employee));
		when(mapper.toResponse(employee)).thenReturn(response);

		// Act
		EmployeeResponse result = service.findById(id);

		// Assert
		assertNotNull(result);
		verify(repository).findById(id);
		verify(mapper).toResponse(employee);
	}

	@Test
	void findById_ShouldThrowException_WhenIdDoesNotExist() {
		// Arrange
		Long id = 1L;
		when(repository.findById(id)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(ResourceNotFoundException.class, () -> service.findById(id));
		verify(repository).findById(id);
	}

	@Test
	void create_ShouldReturnCreatedEmployees() {
		// Arrange
		EmployeeRequest request = new EmployeeRequest();
		List<EmployeeRequest> requestList = Collections.singletonList(request);
		Employee employee = new Employee();
		List<Employee> employeeList = Collections.singletonList(employee);
		EmployeeResponse response = new EmployeeResponse();

		when(mapper.toEntity(request)).thenReturn(employee);
		when(repository.saveAll(anyList())).thenReturn(employeeList);
		when(mapper.toResponse(employee)).thenReturn(response);

		// Act
		List<EmployeeResponse> result = service.create(requestList);

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(mapper).toEntity(request);
		verify(repository).saveAll(anyList());
		verify(mapper).toResponse(employee);
	}

	@Test
	void update_ShouldReturnUpdatedEmployee_WhenIdExists() {
		// Arrange
		Long id = 1L;
		EmployeeRequest request = new EmployeeRequest();
		Employee employee = new Employee();
		EmployeeResponse response = new EmployeeResponse();

		when(repository.findById(id)).thenReturn(Optional.of(employee));
		when(repository.save(employee)).thenReturn(employee);
		when(mapper.toResponse(employee)).thenReturn(response);

		// Act
		EmployeeResponse result = service.update(id, request);

		// Assert
		assertNotNull(result);
		verify(repository).findById(id);
		verify(mapper).updateEntity(employee, request);
		verify(repository).save(employee);
		verify(mapper).toResponse(employee);
	}

	@Test
	void update_ShouldThrowException_WhenIdDoesNotExist() {
		// Arrange
		Long id = 1L;
		EmployeeRequest request = new EmployeeRequest();
		when(repository.findById(id)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(ResourceNotFoundException.class, () -> service.update(id, request));
		verify(repository).findById(id);
		verify(repository, never()).save(any());
	}

	@Test
	void delete_ShouldDeleteEmployee_WhenIdExists() {
		// Arrange
		Long id = 1L;
		Employee employee = new Employee();
		when(repository.findById(id)).thenReturn(Optional.of(employee));

		// Act
		service.delete(id);

		// Assert
		verify(repository).findById(id);
		verify(repository).delete(employee);
	}

	@Test
	void delete_ShouldThrowException_WhenIdDoesNotExist() {
		// Arrange
		Long id = 1L;
		when(repository.findById(id)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(ResourceNotFoundException.class, () -> service.delete(id));
		verify(repository).findById(id);
		verify(repository, never()).delete(any());
	}

	@Test
	void searchByName_ShouldReturnMatchingEmployees() {
		// Arrange
		String name = "John";
		Employee employee = new Employee();
		EmployeeResponse response = new EmployeeResponse();
		when(repository.findByFirstNameContainingIgnoreCase(name)).thenReturn(Collections.singletonList(employee));
		when(mapper.toResponse(employee)).thenReturn(response);

		// Act
		List<EmployeeResponse> result = service.searchByName(name);

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(repository).findByFirstNameContainingIgnoreCase(name);
		verify(mapper).toResponse(employee);
	}
}


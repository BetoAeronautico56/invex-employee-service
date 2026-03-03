package com.invex.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.invex.employee.dto.EmployeeRequest;
import com.invex.employee.dto.EmployeeResponse;
import com.invex.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@Mock
	private EmployeeService service;

	@InjectMocks
	private EmployeeController controller;

	@Test
	void findAll_ShouldReturnListOfEmployees_WhenEmployeesExist() {
		// Arrange
		EmployeeResponse response = new EmployeeResponse();
		response.setId(1L);
		List<EmployeeResponse> expectedList = Collections.singletonList(response);

		when(service.findAll()).thenReturn(expectedList);

		// Act
		ResponseEntity<List<EmployeeResponse>> result = controller.findAll();

		// Assert
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(expectedList, result.getBody());
		verify(service, times(1)).findAll();
	}

	@Test
	void findById_ShouldReturnEmployee_WhenIdExists() {
		// Arrange
		Long id = 1L;
		EmployeeResponse expectedResponse = new EmployeeResponse();
		expectedResponse.setId(id);

		when(service.findById(id)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<EmployeeResponse> result = controller.findById(id);

		// Assert
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(expectedResponse, result.getBody());
		verify(service, times(1)).findById(id);
	}

	@Test
	void create_ShouldReturnCreatedEmployees_WhenRequestIsValid() {
		// Arrange
		EmployeeRequest request = new EmployeeRequest();
		List<EmployeeRequest> requestList = Collections.singletonList(request);
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeResponse> responseList = Collections.singletonList(response);

		when(service.create(requestList)).thenReturn(responseList);

		// Act
		ResponseEntity<List<EmployeeResponse>> result = controller.create(requestList);

		// Assert
		assertNotNull(result);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(responseList, result.getBody());
		verify(service, times(1)).create(requestList);
	}

	@Test
	void update_ShouldReturnUpdatedEmployee_WhenIdExists() {
		// Arrange
		Long id = 1L;
		EmployeeRequest request = new EmployeeRequest();
		EmployeeResponse expectedResponse = new EmployeeResponse();
		expectedResponse.setId(id);

		when(service.update(id, request)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<EmployeeResponse> result = controller.update(id, request);

		// Assert
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(expectedResponse, result.getBody());
		verify(service, times(1)).update(id, request);
	}

	@Test
	void delete_ShouldReturnNoContent_WhenIdExists() {
		// Arrange
		Long id = 1L;
		doNothing().when(service).delete(id);

		// Act
		ResponseEntity<Void> result = controller.delete(id);

		// Assert
		assertNotNull(result);
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		verify(service, times(1)).delete(id);
	}

	@Test
	void search_ShouldReturnMatchingEmployees_WhenNameProvided() {
		// Arrange
		String name = "John";
		EmployeeResponse response = new EmployeeResponse();
		response.setFirstName(name);
		List<EmployeeResponse> expectedList = Collections.singletonList(response);

		when(service.searchByName(name)).thenReturn(expectedList);

		// Act
		ResponseEntity<List<EmployeeResponse>> result = controller.search(name);

		// Assert
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(expectedList, result.getBody());
		verify(service, times(1)).searchByName(name);
	}
}

package com.invex.employee.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.invex.employee.dto.EmployeeRequest;
import com.invex.employee.dto.EmployeeResponse;
import com.invex.employee.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class EmployeeMapperTest {

	private EmployeeMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new EmployeeMapper();
	}

	@Test
	void toEntity_ShouldMapRequestToEntity_WhenRequestIsValid() {
		// Arrange
		EmployeeRequest request = EmployeeRequest.builder()
				.firstName("John")
				.middleName("D")
				.lastName("Doe")
				.secondLastName("Smith")
				.age(30)
				.gender("M")
				.birthDate(LocalDate.of(1990, 1, 1))
				.position("Developer")
				.active(true)
				.build();

		// Act
		Employee entity = mapper.toEntity(request);

		// Assert
		assertNotNull(entity);
		assertEquals(request.getFirstName(), entity.getFirstName());
		assertEquals(request.getMiddleName(), entity.getMiddleName());
		assertEquals(request.getLastName(), entity.getLastName());
		assertEquals(request.getSecondLastName(), entity.getSecondLastName());
		assertEquals(request.getAge(), entity.getAge());
		assertEquals(request.getGender(), entity.getGender());
		assertEquals(request.getBirthDate(), entity.getBirthDate());
		assertEquals(request.getPosition(), entity.getPosition());
		assertEquals(request.getActive(), entity.getActive());
	}

	@Test
	void toResponse_ShouldMapEntityToResponse_WhenEntityIsValid() {
		// Arrange
		Employee entity = Employee.builder()
				.id(1L)
				.firstName("Jane")
				.middleName("M")
				.lastName("Doe")
				.secondLastName("Johnson")
				.age(25)
				.gender("F")
				.birthDate(LocalDate.of(1995, 5, 15))
				.position("Manager")
				.createdAt(LocalDateTime.now())
				.active(true)
				.build();

		// Act
		EmployeeResponse response = mapper.toResponse(entity);

		// Assert
		assertNotNull(response);
		assertEquals(entity.getId(), response.getId());
		assertEquals(entity.getFirstName(), response.getFirstName());
		assertEquals(entity.getMiddleName(), response.getMiddleName());
		assertEquals(entity.getLastName(), response.getLastName());
		assertEquals(entity.getSecondLastName(), response.getSecondLastName());
		assertEquals(entity.getAge(), response.getAge());
		assertEquals(entity.getGender(), response.getGender());
		assertEquals(entity.getBirthDate(), response.getBirthDate());
		assertEquals(entity.getPosition(), response.getPosition());
		assertEquals(entity.getCreatedAt(), response.getCreatedAt());
		assertEquals(entity.getActive(), response.getActive());
	}

	@Test
	void updateEntity_ShouldUpdateEntityFields_WhenRequestIsValid() {
		// Arrange
		Employee entity = Employee.builder().build();
		EmployeeRequest request = EmployeeRequest.builder()
				.firstName("NewName")
				.middleName("NewMiddle")
				.lastName("NewLast")
				.secondLastName("NewSecondLast")
				.age(40)
				.gender("M")
				.birthDate(LocalDate.of(1980, 10, 10))
				.position("Director")
				.active(true)
				.build();

		// Act
		mapper.updateEntity(entity, request);

		// Assert
		assertEquals(request.getFirstName(), entity.getFirstName());
		assertEquals(request.getMiddleName(), entity.getMiddleName());
		assertEquals(request.getLastName(), entity.getLastName());
		assertEquals(request.getSecondLastName(), entity.getSecondLastName());
		assertEquals(request.getAge(), entity.getAge());
		assertEquals(request.getGender(), entity.getGender());
		assertEquals(request.getBirthDate(), entity.getBirthDate());
		assertEquals(request.getPosition(), entity.getPosition());
		assertEquals(request.getActive(), entity.getActive());
	}
}

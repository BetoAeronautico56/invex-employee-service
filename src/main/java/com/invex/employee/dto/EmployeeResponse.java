package com.invex.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

	@Schema(description = "Unique identifier of the employee", example = "1")
	private Long id;
	@Schema(description = "First name of the employee", example = "John")
	private String firstName;
	@Schema(description = "Middle name of the employee", example = "Fitzgerald")
	private String middleName;
	@Schema(description = "Last name of the employee", example = "Doe")
	private String lastName;
	@Schema(description = "Second last name of the employee", example = "Smith")
	private String secondLastName;
	@Schema(description = "Age of the employee", example = "30")
	private Integer age;
	@Schema(description = "Gender of the employee", example = "M")
	private String gender;
	@Schema(description = "Birth date of the employee", example = "25-12-1990")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;
	@Schema(description = "Job position of the employee", example = "Software Engineer")
	private String position;
	@Schema(description = "Timestamp when the employee was created", example = "2023-10-27T10:00:00")
	private LocalDateTime createdAt;
	@Schema(description = "Indicates if the employee is currently active", example = "true")
	private Boolean active;
}
package com.invex.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

	@Schema(description = "First name of the employee", example = "John")
	@NotBlank(message = "First name is required")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "First name must contain only letters and spaces")
	private String firstName;

	@Schema(description = "Middle name of the employee (optional)", example = "Fitzgerald")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Middle name must contain only letters and spaces")
	private String middleName;

	@Schema(description = "Last name of the employee", example = "Doe")
	@NotBlank(message = "Last name is required")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Last name must contain only letters and spaces")
	private String lastName;

	@Schema(description = "Second last name of the employee", example = "Smith")
	@NotBlank(message = "Second last name is required")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Second last name must contain only letters and spaces")
	private String secondLastName;

	@Schema(description = "Age of the employee", example = "30")
	@NotNull(message = "Age is required")
	@Min(value = 18, message = "Employee must be at least 18 years old")
	private Integer age;

	@Schema(description = "Gender of the employee", example = "M")
	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "[MF]", message = "Gender must be M (Male) or F (Female)")
	private String gender;

	@Schema(description = "Birth date of the employee in dd-MM-yyyy format", example = "25-12-1990")
	@NotNull(message = "Birth date is required")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;

	@Schema(description = "Job position of the employee", example = "Software Engineer")
	@NotBlank(message = "Position is required")
	private String position;

	@Schema(description = "Indicates if the employee is currently active", example = "true")
	@NotNull(message = "Active field is required and must be true or false")
	private Boolean active;
}
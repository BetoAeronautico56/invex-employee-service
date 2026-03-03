package com.invex.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

	@NotBlank
	private String firstName;

	private String middleName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String secondLastName;

	@NotNull
	@Min(18)
	private Integer age;

	@NotBlank
	@Size(min = 1, max = 1)
	private String gender;

	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;

	@NotBlank
	private String position;

	private Boolean active;
}
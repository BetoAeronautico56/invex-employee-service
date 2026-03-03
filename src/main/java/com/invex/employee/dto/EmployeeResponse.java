package com.invex.employee.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String secondLastName;
	private Integer age;
	private String gender;
	private LocalDate birthDate;
	private String position;
	private LocalDateTime createdAt;
	private Boolean active;
}
package com.invex.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "second_last_name", nullable = false)
	private String secondLastName;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false, length = 1)
	private String gender;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(nullable = false)
	private String position;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private Boolean active;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		if (this.active == null) {
			this.active = true;
		}
	}
}
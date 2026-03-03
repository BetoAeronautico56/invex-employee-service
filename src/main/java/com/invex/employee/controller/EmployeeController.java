package com.invex.employee.controller;

import com.invex.employee.dto.*;
import com.invex.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Management", description = "APIs for creating, retrieving, updating, and deleting employees")
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService service;

	@GetMapping
	@Operation(summary = "Retrieve all employees", description = "Gets a list of all registered employees.")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class)) })
	public ResponseEntity<List<EmployeeResponse>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retrieve an employee by ID", description = "Gets a single employee's details by their unique ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successfully retrieved employee", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found with the given ID", content = @Content) })
	public ResponseEntity<EmployeeResponse> findById(
			@Parameter(description = "ID of the employee to be retrieved", required = true, example = "1") @PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	@Operation(summary = "Create one or more new employees", description = "Creates new employees based on the provided list of requests.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Employees created successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input data provided", content = @Content) })
	public ResponseEntity<List<EmployeeResponse>> create(@Valid @RequestBody List<EmployeeRequest> request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing employee", description = "Updates the details of an employee specified by their ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Employee updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input data provided", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found with the given ID", content = @Content) })
	public ResponseEntity<EmployeeResponse> update(
			@Parameter(description = "ID of the employee to be updated", required = true, example = "1") @PathVariable Long id,
			@Valid @RequestBody EmployeeRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete an employee", description = "Deletes an employee specified by their ID.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Employee deleted successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found with the given ID", content = @Content) })
	public ResponseEntity<Void> delete(
			@Parameter(description = "ID of the employee to be deleted", required = true, example = "1") @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	@Operation(summary = "Search employees by name", description = "Searches for employees whose first name contains the given search term (case-insensitive).")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved list of matching employees", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class)) })
	public ResponseEntity<List<EmployeeResponse>> search(
			@Parameter(description = "Name or partial name to search for", required = true, example = "John") @RequestParam String name) {
		return ResponseEntity.ok(service.searchByName(name));
	}
}
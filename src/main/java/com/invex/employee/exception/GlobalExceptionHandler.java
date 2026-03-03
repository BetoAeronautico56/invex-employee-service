package com.invex.employee.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Global exception handler to capture and process exceptions across the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles resource not found exceptions.
	 *
	 * @param ex the exception thrown
	 * @return a 404 Not Found response with the error message
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
	}

	/**
	 * Handles validation errors for request bodies (e.g., @Valid on objects).
	 *
	 * @param ex the validation exception
	 * @return a 400 Bad Request response with a map of field errors
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(errors);
	}

	/**
	 * Handles validation errors for method parameters or lists (e.g., @Valid on List).
	 *
	 * @param ex      the handler validation exception
	 * @param request the HTTP request
	 * @return a 400 Bad Request response with detailed error information
	 */
	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<?> handleMethodValidation(HandlerMethodValidationException ex, HttpServletRequest request) {
		Map<String, Object> response = new LinkedHashMap<>();
		List<String> details = new ArrayList<>();

		ex.getAllValidationResults().forEach(result -> result.getResolvableErrors().forEach(error -> {
			String fieldName = (error instanceof FieldError fieldError) ? fieldError.getField() : result.getMethodParameter().getParameterName();
			details.add(fieldName + ": " + error.getDefaultMessage());
		}));

		response.put("timestamp", ZonedDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		response.put("trace", ex.getClass().getName());
		response.put("message", String.join(", ", details));
		response.put("path", request.getRequestURI());

		return ResponseEntity.badRequest().body(response);
	}
}